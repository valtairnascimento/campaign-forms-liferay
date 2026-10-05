package io.github.valtairnascimento.campaignforms.headless.admin.internal.dto.converter;

import io.github.valtairnascimento.campaignforms.constants.FieldValueType;
import io.github.valtairnascimento.campaignforms.field.FieldType;
import io.github.valtairnascimento.campaignforms.field.FieldTypeRegistry;
import io.github.valtairnascimento.campaignforms.headless.admin.dto.v1_0.Submission;
import io.github.valtairnascimento.campaignforms.model.FormField;
import io.github.valtairnascimento.campaignforms.model.FormVersion;
import io.github.valtairnascimento.campaignforms.model.SubmissionValue;
import io.github.valtairnascimento.campaignforms.service.FormFieldLocalService;
import io.github.valtairnascimento.campaignforms.service.FormVersionLocalService;
import io.github.valtairnascimento.campaignforms.service.SubmissionValueLocalService;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = SubmissionDTOConverter.class)
public class SubmissionDTOConverter {
	public Submission toDTO(
			io.github.valtairnascimento.campaignforms.model.Submission submission)
		throws PortalException {
		return toDTOs(
			Collections.singletonList(submission)
		).get(
			0
		);
	}

	public List<Submission> toDTOs(
			List<io.github.valtairnascimento.campaignforms.model.Submission> submissions)
		throws PortalException {
		Map<Long, VersionInfo> versionInfos = new HashMap<>();

		List<Submission> dtos = new ArrayList<>(submissions.size());

		for (io.github.valtairnascimento.campaignforms.model.Submission submission :
				submissions) {
			VersionInfo versionInfo = versionInfos.get(
				submission.getFormVersionId());

			if (versionInfo == null) {
				versionInfo = _getVersionInfo(submission.getFormVersionId());

				versionInfos.put(submission.getFormVersionId(), versionInfo);
			}

			dtos.add(_toDTO(submission, versionInfo));
		}

		return dtos;
	}

	private VersionInfo _getVersionInfo(long formVersionId)
		throws PortalException {
		FormVersion formVersion = _formVersionLocalService.getFormVersion(
			formVersionId);

		List<String> fieldKeys = new ArrayList<>();
		Set<String> multiValuedFieldKeys = new HashSet<>();

		for (FormField formField :
				_formFieldLocalService.getFormFields(formVersionId)) {
			fieldKeys.add(formField.getFieldKey());

			FieldType fieldType = _fieldTypeRegistry.getFieldType(
				formField.getType());

			if ((fieldType != null) && fieldType.isMultiValued()) {
				multiValuedFieldKeys.add(formField.getFieldKey());
			}
		}

		return new VersionInfo(
			formVersion.getVersion(), fieldKeys, multiValuedFieldKeys);
	}

	private String _nullIfEmpty(String value) {
		if (Validator.isNull(value)) {
			return null;
		}

		return value;
	}

	private Submission _toDTO(
		io.github.valtairnascimento.campaignforms.model.Submission submission,
		VersionInfo versionInfo) {
		Submission dto = new Submission();

		dto.setCampaignId(submission.getCampaignId());
		dto.setConsentDate(submission.getConsentDate());
		dto.setConsentVersion(_nullIfEmpty(submission.getConsentVersion()));
		dto.setDateCreated(submission.getCreateDate());
		dto.setFormVersion(versionInfo._version);
		dto.setFormVersionId(submission.getFormVersionId());
		dto.setId(submission.getSubmissionId());
		dto.setLanguageId(_nullIfEmpty(submission.getLanguageId()));
		dto.setReferrer(_nullIfEmpty(submission.getReferrer()));
		dto.setSourceUrl(_nullIfEmpty(submission.getSourceUrl()));
		dto.setStatus(submission.getStatus());
		dto.setUtmCampaign(_nullIfEmpty(submission.getUtmCampaign()));
		dto.setUtmContent(_nullIfEmpty(submission.getUtmContent()));
		dto.setUtmMedium(_nullIfEmpty(submission.getUtmMedium()));
		dto.setUtmSource(_nullIfEmpty(submission.getUtmSource()));
		dto.setUtmTerm(_nullIfEmpty(submission.getUtmTerm()));
		dto.setValues(_toValues(submission, versionInfo));

		return dto;
	}

	private Object _toValue(SubmissionValue submissionValue) {
		FieldValueType fieldValueType = FieldValueType.parse(
			submissionValue.getValueType());

		if (fieldValueType == FieldValueType.BOOLEAN) {
			return submissionValue.isBooleanValue();
		}

		if (fieldValueType == FieldValueType.NUMBER) {
			return submissionValue.getNumberValue();
		}

		if (Validator.isNotNull(submissionValue.getTextValue())) {
			return submissionValue.getTextValue();
		}

		return submissionValue.getStringValue();
	}

	private Map<String, Object> _toValues(
		io.github.valtairnascimento.campaignforms.model.Submission submission,
		VersionInfo versionInfo) {
		Map<String, List<Object>> valuesByFieldKey = new HashMap<>();

		for (SubmissionValue submissionValue :
				_submissionValueLocalService.getSubmissionValues(
					submission.getSubmissionId())) {
			valuesByFieldKey.computeIfAbsent(
				submissionValue.getFieldKey(), key -> new ArrayList<>()
			).add(
				_toValue(submissionValue)
			);
		}

		Map<String, Object> values = new LinkedHashMap<>();

		for (String fieldKey : versionInfo._fieldKeys) {
			List<Object> fieldValues = valuesByFieldKey.get(fieldKey);

			if (fieldValues == null) {
				continue;
			}

			if (versionInfo._multiValuedFieldKeys.contains(fieldKey)) {
				values.put(fieldKey, fieldValues);
			}
			else {
				values.put(fieldKey, fieldValues.get(0));
			}
		}

		return values;
	}

	@Reference
	private FieldTypeRegistry _fieldTypeRegistry;

	@Reference
	private FormFieldLocalService _formFieldLocalService;

	@Reference
	private FormVersionLocalService _formVersionLocalService;

	@Reference
	private SubmissionValueLocalService _submissionValueLocalService;

	private static class VersionInfo {
		private VersionInfo(
			int version, List<String> fieldKeys,
			Set<String> multiValuedFieldKeys) {
			_version = version;
			_fieldKeys = fieldKeys;
			_multiValuedFieldKeys = multiValuedFieldKeys;
		}

		private final List<String> _fieldKeys;
		private final Set<String> _multiValuedFieldKeys;
		private final int _version;
	}

}
