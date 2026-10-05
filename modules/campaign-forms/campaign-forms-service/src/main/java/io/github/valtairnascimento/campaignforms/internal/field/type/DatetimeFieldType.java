package io.github.valtairnascimento.campaignforms.internal.field.type;

import io.github.valtairnascimento.campaignforms.constants.FieldValueType;
import io.github.valtairnascimento.campaignforms.field.FieldDefinition;
import io.github.valtairnascimento.campaignforms.field.FieldError;
import io.github.valtairnascimento.campaignforms.field.FieldType;
import io.github.valtairnascimento.campaignforms.field.FieldValue;
import io.github.valtairnascimento.campaignforms.field.FieldValueException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

import java.util.Collections;
import java.util.Date;
import java.util.Set;
import java.util.TimeZone;

import org.osgi.service.component.annotations.Component;

@Component(
	property = FieldType.PROPERTY_KEY + "=" + DatetimeFieldType.KEY,
	service = FieldType.class
)
public class DatetimeFieldType extends BaseSingleValueFieldType {
	public static final String KEY = "datetime";

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public FieldValueType getValueType() {
		return FieldValueType.DATETIME;
	}

	@Override
	protected Set<String> getSupportedRules() {
		return Collections.emptySet();
	}

	@Override
	protected FieldValue toValue(
			FieldDefinition fieldDefinition, String rawValue,
			TimeZone timeZone)
		throws FieldValueException {
		Instant instant = _parse(rawValue, timeZone);

		int year = instant.atOffset(
			ZoneOffset.UTC
		).getYear();

		if ((year < _MIN_YEAR) || (year > _MAX_YEAR)) {
			throw new FieldValueException(
				FieldError.INVALID_DATE, "field-error-invalid-date");
		}

		instant = instant.truncatedTo(ChronoUnit.SECONDS);

		return FieldValue.ofDate(
			FieldValueType.DATETIME, Date.from(instant),
			DateTimeFormatter.ISO_INSTANT.format(instant));
	}

	private Instant _parse(String rawValue, TimeZone timeZone)
		throws FieldValueException {
		try {
			return OffsetDateTime.parse(
				rawValue
			).toInstant();
		}
		catch (DateTimeParseException dateTimeParseException) {
		}

		try {
			ZoneId zoneId = ZoneOffset.UTC;

			if (timeZone != null) {
				zoneId = timeZone.toZoneId();
			}

			return LocalDateTime.parse(
				rawValue
			).atZone(
				zoneId
			).toInstant();
		}
		catch (DateTimeParseException dateTimeParseException) {
			throw new FieldValueException(
				FieldError.INVALID_DATE, "field-error-invalid-date");
		}
	}

	private static final int _MAX_YEAR = 2200;

	private static final int _MIN_YEAR = 1900;
}
