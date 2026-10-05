package io.github.valtairnascimento.campaignforms.internal.submission;

import io.github.valtairnascimento.campaignforms.exception.InvalidFieldException;

import org.junit.Assert;
import org.junit.Test;

public class SubmissionDataSanitizerTest {
	@Test
	public void testNormalizeIdempotencyKey() throws Exception {
		Assert.assertNull(SubmissionDataSanitizer.normalizeIdempotencyKey(null));
		Assert.assertNull(SubmissionDataSanitizer.normalizeIdempotencyKey("  "));
		Assert.assertEquals(
			"0f8fad5b-d9cb-469f-a165-70867728950e",
			SubmissionDataSanitizer.normalizeIdempotencyKey(
				" 0f8fad5b-d9cb-469f-a165-70867728950e "));
	}

	@Test(expected = InvalidFieldException.class)
	public void testNormalizeIdempotencyKeyRejectsInvalidCharacters()
		throws Exception {
		SubmissionDataSanitizer.normalizeIdempotencyKey("chave com espaço");
	}

	@Test
	public void testNormalizeLanguageId() {
		Assert.assertEquals(
			"en_US",
			SubmissionDataSanitizer.normalizeLanguageId("en_US", "pt_BR"));
		Assert.assertEquals(
			"pt_BR",
			SubmissionDataSanitizer.normalizeLanguageId(
				"<script>", "pt_BR"));
		Assert.assertEquals(
			"pt_BR", SubmissionDataSanitizer.normalizeLanguageId(null, "pt_BR"));
	}

	@Test
	public void testSanitizeText() {
		Assert.assertEquals("", SubmissionDataSanitizer.sanitizeText(null, 10));
		Assert.assertEquals(
			"Mozilla/5.0",
			SubmissionDataSanitizer.sanitizeText(" Mozilla/5.0\r\n", 100));
		Assert.assertEquals(
			"abc", SubmissionDataSanitizer.sanitizeText("abcdef", 3));
	}

	@Test
	public void testSanitizeUrlKeepsOnlyHttpWithoutQueryString() {
		Assert.assertEquals(
			"https://www.example.org/campaign/encontro",
			SubmissionDataSanitizer.sanitizeUrl(
				"https://www.example.org/campaign/encontro?token=x#form"));
		Assert.assertEquals(
			"http://exemplo.com/a",
			SubmissionDataSanitizer.sanitizeUrl("http://exemplo.com/a#b?c"));
		Assert.assertEquals(
			"", SubmissionDataSanitizer.sanitizeUrl("javascript:alert(1)"));
		Assert.assertEquals("", SubmissionDataSanitizer.sanitizeUrl(null));

		StringBuilder sb = new StringBuilder("https://exemplo.com/");

		while (sb.length() < 3000) {
			sb.append('a');
		}

		Assert.assertEquals(
			SubmissionDataSanitizer.MAX_URL_LENGTH,
			SubmissionDataSanitizer.sanitizeUrl(
				sb.toString()
			).length());
	}

}
