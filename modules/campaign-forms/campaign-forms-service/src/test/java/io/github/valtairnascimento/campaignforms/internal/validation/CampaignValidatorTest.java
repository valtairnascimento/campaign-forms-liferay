package io.github.valtairnascimento.campaignforms.internal.validation;

import io.github.valtairnascimento.campaignforms.exception.CampaignDatesException;
import io.github.valtairnascimento.campaignforms.exception.CampaignNameException;
import io.github.valtairnascimento.campaignforms.exception.CampaignSlugException;
import io.github.valtairnascimento.campaignforms.exception.FieldKeyException;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class CampaignValidatorTest {
	@Test
	public void testEmailOrSubmissionPlaceholder() {
		Assert.assertTrue(
			CampaignValidator.isEmailOrSubmissionPlaceholder(
				"rh@example.com"));
		Assert.assertTrue(
			CampaignValidator.isEmailOrSubmissionPlaceholder(
				"${submission.email}"));

		Assert.assertFalse(
			CampaignValidator.isEmailOrSubmissionPlaceholder(
				"${campaign.name}"));
		Assert.assertFalse(
			CampaignValidator.isEmailOrSubmissionPlaceholder(
				"${submission.email}.attacker.com"));
		Assert.assertFalse(
			CampaignValidator.isEmailOrSubmissionPlaceholder("not an email"));
	}

	@Test(expected = CampaignDatesException.class)
	public void testEndDateBeforeStartDate() throws Exception {
		Date startDate = new Date();

		CampaignValidator.validateDates(
			startDate, new Date(startDate.getTime() - 1000));
	}

	@Test
	public void testKeys() {
		Assert.assertTrue(CampaignValidator.isValidKey("encontro-rh"));
		Assert.assertTrue(CampaignValidator.isValidKey("evento_regional"));
		Assert.assertTrue(CampaignValidator.isValidKey("londrina2026"));

		Assert.assertFalse(CampaignValidator.isValidKey(""));
		Assert.assertFalse(CampaignValidator.isValidKey("Encontro"));
		Assert.assertFalse(CampaignValidator.isValidKey("-rh"));
		Assert.assertFalse(CampaignValidator.isValidKey("rh--londrina"));
		Assert.assertFalse(CampaignValidator.isValidKey(_repeat("a", 76)));
	}

	@Test
	public void testRedirectUrl() {
		Assert.assertTrue(CampaignValidator.isValidRedirectUrl(null));
		Assert.assertTrue(CampaignValidator.isValidRedirectUrl("/obrigado"));
		Assert.assertTrue(
			CampaignValidator.isValidRedirectUrl(
				"https://www.example.com/obrigado"));

		Assert.assertFalse(
			CampaignValidator.isValidRedirectUrl("//evil.example.com"));
		Assert.assertFalse(
			CampaignValidator.isValidRedirectUrl("/\\evil.example.com"));
		Assert.assertFalse(
			CampaignValidator.isValidRedirectUrl("javascript:alert(1)"));
		Assert.assertFalse(
			CampaignValidator.isValidRedirectUrl("ftp://example.com"));
	}

	@Test
	public void testValidDatesAndOpenEndedPeriod() throws Exception {
		Date startDate = new Date();

		CampaignValidator.validateDates(
			startDate, new Date(startDate.getTime() + 1000));
		CampaignValidator.validateDates(null, new Date());
		CampaignValidator.validateDates(new Date(), null);
	}

	@Test
	public void testValidFieldKeys() throws Exception {
		CampaignValidator.validateFieldKey("nome");
		CampaignValidator.validateFieldKey("email");
		CampaignValidator.validateFieldKey("tipoPessoa");
		CampaignValidator.validateFieldKey("cargo_atual2");
	}

	@Test
	public void testValidSlugs() throws Exception {
		CampaignValidator.validateSlug("encontro-rh-londrina");
		CampaignValidator.validateSlug("evento2026");
	}

	@Test
	public void testInvalidFieldKeys() {
		_assertInvalidFieldKey(null, "field-key-is-required");
		_assertInvalidFieldKey("", "field-key-is-required");
		_assertInvalidFieldKey("Nome", "field-key-is-invalid");
		_assertInvalidFieldKey("1nome", "field-key-is-invalid");
		_assertInvalidFieldKey("e-mail", "field-key-is-invalid");
		_assertInvalidFieldKey("nome completo", "field-key-is-invalid");
		_assertInvalidFieldKey(_repeat("a", 51), "field-key-is-invalid");

		_assertInvalidFieldKey("submissionId", "field-key-is-reserved");
		_assertInvalidFieldKey("createdAt", "field-key-is-reserved");
		_assertInvalidFieldKey("status", "field-key-is-reserved");
		_assertInvalidFieldKey("utmsource", "field-key-is-reserved");
	}

	@Test
	public void testInvalidNames() {
		_assertInvalidName(null, "campaign-name-is-required");
		_assertInvalidName("   ", "campaign-name-is-required");
		_assertInvalidName(_repeat("a", 151), "campaign-name-is-too-long");
	}

	@Test
	public void testInvalidSlugs() {
		_assertInvalidSlug(null, "slug-is-required");
		_assertInvalidSlug("Encontro-RH", "slug-is-invalid");
		_assertInvalidSlug("encontro rh", "slug-is-invalid");
		_assertInvalidSlug("encontro_rh", "slug-is-invalid");
		_assertInvalidSlug("-encontro", "slug-is-invalid");
		_assertInvalidSlug("encontro-", "slug-is-invalid");
		_assertInvalidSlug("encontro--rh", "slug-is-invalid");
		_assertInvalidSlug("encontro/rh", "slug-is-invalid");
		_assertInvalidSlug(_repeat("a", 151), "slug-is-invalid");
		_assertInvalidSlug("preview", "slug-is-reserved");
		_assertInvalidSlug("admin", "slug-is-reserved");
	}

	private void _assertInvalidFieldKey(String fieldKey, String messageKey) {
		try {
			CampaignValidator.validateFieldKey(fieldKey);

			Assert.fail("Expected FieldKeyException for " + fieldKey);
		}
		catch (FieldKeyException fieldKeyException) {
			Assert.assertEquals(messageKey, fieldKeyException.getMessage());
		}
	}

	private void _assertInvalidName(String name, String messageKey) {
		try {
			CampaignValidator.validateName(name);

			Assert.fail("Expected CampaignNameException for " + name);
		}
		catch (CampaignNameException campaignNameException) {
			Assert.assertEquals(messageKey, campaignNameException.getMessage());
		}
	}

	private void _assertInvalidSlug(String slug, String messageKey) {
		try {
			CampaignValidator.validateSlug(slug);

			Assert.fail("Expected CampaignSlugException for " + slug);
		}
		catch (CampaignSlugException campaignSlugException) {
			Assert.assertEquals(messageKey, campaignSlugException.getMessage());
		}
	}

	private String _repeat(String value, int times) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < times; i++) {
			sb.append(value);
		}

		return sb.toString();
	}

}
