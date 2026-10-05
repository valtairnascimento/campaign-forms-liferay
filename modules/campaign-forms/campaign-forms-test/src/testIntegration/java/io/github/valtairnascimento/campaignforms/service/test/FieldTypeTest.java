package io.github.valtairnascimento.campaignforms.service.test;

import io.github.valtairnascimento.campaignforms.constants.FieldValueType;
import io.github.valtairnascimento.campaignforms.exception.FormFieldConfigurationException;
import io.github.valtairnascimento.campaignforms.field.FieldDefinition;
import io.github.valtairnascimento.campaignforms.field.FieldError;
import io.github.valtairnascimento.campaignforms.field.FieldPreset;
import io.github.valtairnascimento.campaignforms.field.FieldPresetRegistry;
import io.github.valtairnascimento.campaignforms.field.FieldType;
import io.github.valtairnascimento.campaignforms.field.FieldTypeRegistry;
import io.github.valtairnascimento.campaignforms.field.FieldValue;
import io.github.valtairnascimento.campaignforms.field.FieldValueException;
import io.github.valtairnascimento.campaignforms.input.FormFieldInput;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class FieldTypeTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void testBooleanRequiredMustBeTrue() throws Exception {
		FieldDefinition fieldDefinition = _definition(
			"aceite", "boolean", true, "{}");

		Assert.assertTrue(
			_toValues(
				fieldDefinition, "on"
			).get(
				0
			).isBooleanValue());
		_assertError(fieldDefinition, FieldError.REQUIRED, "false");
		_assertError(fieldDefinition, FieldError.INVALID_VALUE, "talvez");
	}

	@Test
	public void testBuiltInTypesAreRegistered() {
		List<String> keys = new ArrayList<>();

		for (FieldType fieldType : _fieldTypeRegistry.getFieldTypes()) {
			keys.add(fieldType.getKey());
		}

		Assert.assertTrue(
			keys.toString(),
			keys.containsAll(
				Arrays.asList(
					"boolean", "checkbox", "date", "datetime", "email",
					"hidden", "multiSelect", "number", "radio", "select",
					"tel", "text", "textarea")));
		Assert.assertNull(_fieldTypeRegistry.getFieldType("cpf"));
	}

	@Test
	public void testDate() throws Exception {
		FieldDefinition fieldDefinition = _definition(
			"nascimento", "date", false,
			"{\"min\":\"1990-01-01\",\"max\":\"2030-12-31\"}");

		FieldValue fieldValue = _toValues(
			fieldDefinition, "2026-10-01"
		).get(
			0
		);

		Assert.assertEquals(FieldValueType.DATE, fieldValue.getValueType());
		Assert.assertEquals("2026-10-01", fieldValue.getStringValue());

		_assertError(fieldDefinition, FieldError.INVALID_DATE, "01/10/2026");
		_assertError(fieldDefinition, FieldError.TOO_SMALL, "1980-05-05");
		_assertError(fieldDefinition, FieldError.TOO_LARGE, "2031-01-01");
	}

	@Test
	public void testDatetimeWithoutOffsetUsesTimeZone() throws Exception {
		FieldValue fieldValue = _fieldTypeRegistry.getFieldType(
			"datetime"
		).toValues(
			_definition("inicio", "datetime", false, "{}"),
			Collections.singletonList("2026-10-01T10:30"),
			TimeZone.getTimeZone("America/Sao_Paulo")
		).get(
			0
		);

		Assert.assertEquals("2026-10-01T13:30:00Z", fieldValue.getStringValue());
	}

	@Test
	public void testEmailIsNormalizedToLowerCase() throws Exception {
		FieldDefinition fieldDefinition = _definition(
			"email", "email", true, "{}");

		Assert.assertEquals(
			"maria@example.org",
			_toValues(
				fieldDefinition, "Maria@EXAMPLE.org"
			).get(
				0
			).getStringValue());
		_assertError(fieldDefinition, FieldError.INVALID_EMAIL, "maria@");
	}

	@Test
	public void testInvalidDefinitionsAreRejected() throws Exception {
		_assertInvalidDefinition(
			_definition("nome", "text", false, "{\"foo\":1}"));
		_assertInvalidDefinition(
			_definition("nome", "text", false, "{\"pattern\":\"(a+)+$\"}"));
		_assertInvalidDefinition(
			_definition(
				"nome", "text", false, "{\"minLength\":10,\"maxLength\":5}"));
		_assertInvalidDefinition(
			_definition("nome", "text", false, "{\"maxLength\":9999}"));
		_assertInvalidDefinition(
			_definition("idade", "number", false, "{\"min\":10,\"max\":1}"));
		_assertInvalidDefinition(
			_definition("cidade", "select", false, "{}"));
	}

	@Test
	public void testMultiSelectDeduplicatesAndLimitsSelection()
		throws Exception {
		FieldDefinition fieldDefinition = new FieldDefinition(
			0, "interesses", "multiSelect", "Interesses", false, null,
			JSONFactoryUtil.createJSONObject("{\"maxSelected\":2}"),
			JSONFactoryUtil.createJSONObject(), Arrays.asList("a", "b", "c"),
			false);

		List<FieldValue> fieldValues = _fieldTypeRegistry.getFieldType(
			"multiSelect"
		).toValues(
			fieldDefinition, Arrays.asList("a", "b", "a"), null
		);

		Assert.assertEquals(fieldValues.toString(), 2, fieldValues.size());

		_assertError(
			fieldDefinition, FieldError.TOO_MANY_SELECTED, "a", "b", "c");
		_assertError(fieldDefinition, FieldError.INVALID_OPTION, "x");
	}

	@Test
	public void testNumber() throws Exception {
		FieldDefinition fieldDefinition = _definition(
			"participantes", "number", false,
			"{\"min\":1,\"max\":10,\"integer\":true}");

		Assert.assertEquals(
			new BigDecimal("3"),
			_toValues(
				fieldDefinition, "3,0"
			).get(
				0
			).getNumberValue());

		_assertError(fieldDefinition, FieldError.INVALID_NUMBER, "1.000,5");
		_assertError(fieldDefinition, FieldError.INVALID_NUMBER, "2.5");
		_assertError(fieldDefinition, FieldError.TOO_LARGE, "11");
		_assertError(fieldDefinition, FieldError.TOO_SMALL, "0");
	}

	@Test
	public void testPresets() throws Exception {
		List<String> keys = new ArrayList<>();

		for (FieldPreset fieldPreset : _fieldPresetRegistry.getFieldPresets()) {
			keys.add(fieldPreset.getKey());
		}

		Assert.assertEquals(
			Arrays.asList(
				"nome", "email", "telefone", "empresa", "cargo", "cidade",
				"estado"),
			keys);

		FormFieldInput formFieldInput = _fieldPresetRegistry.getFieldPreset(
			"estado"
		).toFormFieldInput(
			LocaleUtil.BRAZIL
		);

		Assert.assertEquals("Estado", formFieldInput.getLabel());
		Assert.assertEquals("select", formFieldInput.getType());
		Assert.assertEquals(27, formFieldInput.getOptions().size());

		FormFieldInput emailFormFieldInput =
			_fieldPresetRegistry.getFieldPreset(
				"email"
			).toFormFieldInput(
				LocaleUtil.BRAZIL
			);

		Assert.assertEquals("email", emailFormFieldInput.getFieldKey());
		Assert.assertTrue(emailFormFieldInput.isPersonalData());
		Assert.assertTrue(emailFormFieldInput.isRequired());
	}

	@Test
	public void testTel() throws Exception {
		FieldDefinition fieldDefinition = _definition(
			"telefone", "tel", false, "{}");

		Assert.assertEquals(
			"41999990000",
			_toValues(
				fieldDefinition, "(41) 99999-0000"
			).get(
				0
			).getStringValue());
		Assert.assertEquals(
			"+5541999990000",
			_toValues(
				fieldDefinition, "+55 41 99999-0000"
			).get(
				0
			).getStringValue());

		_assertError(fieldDefinition, FieldError.INVALID_PHONE, "1234");
		_assertError(fieldDefinition, FieldError.INVALID_PHONE, "ligue já");
	}

	@Test
	public void testTextLengthAndPattern() throws Exception {
		FieldDefinition fieldDefinition = _definition(
			"cep", "text", false,
			"{\"minLength\":8,\"maxLength\":9,\"pattern\":\"cep\"}");

		Assert.assertEquals(
			"80000-000",
			_toValues(
				fieldDefinition, "80000-000"
			).get(
				0
			).getStringValue());

		_assertError(fieldDefinition, FieldError.TOO_SHORT, "800");
		_assertError(fieldDefinition, FieldError.TOO_LONG, "80000-0000");
		_assertError(fieldDefinition, FieldError.PATTERN_MISMATCH, "8000a-000");
	}

	@Test
	public void testTextareaKeepsLongTextInTextValue() throws Exception {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 700; i++) {
			sb.append('a');
		}

		FieldValue fieldValue = _toValues(
			_definition("mensagem", "textarea", false, "{}"), sb.toString()
		).get(
			0
		);

		Assert.assertEquals(
			FieldValue.MAX_STRING_VALUE_LENGTH,
			fieldValue.getStringValue(
			).length());
		Assert.assertEquals(
			700,
			fieldValue.getTextValue(
			).length());
	}

	private void _assertError(
			FieldDefinition fieldDefinition, String code, String... rawValues)
		throws Exception {
		try {
			_fieldTypeRegistry.getFieldType(
				fieldDefinition.getType()
			).toValues(
				fieldDefinition, Arrays.asList(rawValues), null
			);

			Assert.fail(
				"Expected " + code + " for " + Arrays.toString(rawValues));
		}
		catch (FieldValueException fieldValueException) {
			Assert.assertEquals(code, fieldValueException.getCode());
		}
	}

	private void _assertInvalidDefinition(FieldDefinition fieldDefinition) {
		try {
			_fieldTypeRegistry.getFieldType(
				fieldDefinition.getType()
			).validateDefinition(
				fieldDefinition
			);

			Assert.fail(
				"Expected invalid definition: " +
					fieldDefinition.getValidation());
		}
		catch (FormFieldConfigurationException
					formFieldConfigurationException) {
		}
	}

	private FieldDefinition _definition(
			String fieldKey, String type, boolean required, String validation)
		throws Exception {
		return new FieldDefinition(
			0, fieldKey, type, fieldKey, required, null,
			JSONFactoryUtil.createJSONObject(validation),
			JSONFactoryUtil.createJSONObject(), Collections.emptyList(),
			false);
	}

	private List<FieldValue> _toValues(
			FieldDefinition fieldDefinition, String rawValue)
		throws Exception {
		return _fieldTypeRegistry.getFieldType(
			fieldDefinition.getType()
		).toValues(
			fieldDefinition, Collections.singletonList(rawValue), null
		);
	}

	@Inject
	private FieldPresetRegistry _fieldPresetRegistry;

	@Inject
	private FieldTypeRegistry _fieldTypeRegistry;
}
