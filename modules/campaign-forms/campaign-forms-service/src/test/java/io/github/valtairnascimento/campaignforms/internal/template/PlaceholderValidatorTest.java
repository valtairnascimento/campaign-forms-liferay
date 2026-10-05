package io.github.valtairnascimento.campaignforms.internal.template;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import org.junit.Assert;
import org.junit.Test;

public class PlaceholderValidatorTest {
	@Test
	public void testFindPlaceholders() {
		Assert.assertEquals(
			Arrays.asList("${campaign.name}", "${submission.email}"),
			PlaceholderValidator.findPlaceholders(
				"Inscrição em ${campaign.name}: ${submission.email} " +
					"(${campaign.name})"));
		Assert.assertEquals(
			Collections.emptyList(), PlaceholderValidator.findPlaceholders(null));
	}

	@Test
	public void testGetSubmissionFieldKey() {
		Assert.assertEquals(
			"email",
			PlaceholderValidator.getSubmissionFieldKey("${submission.email}"));
		Assert.assertNull(
			PlaceholderValidator.getSubmissionFieldKey("${submission.table}"));
		Assert.assertNull(
			PlaceholderValidator.getSubmissionFieldKey("${campaign.name}"));
	}

	@Test
	public void testUnknownPlaceholders() {
		PlaceholderValidator placeholderValidator = new PlaceholderValidator(
			new HashSet<>(Arrays.asList("nome", "email")),
			Collections.singleton("city"));

		Assert.assertEquals(
			Collections.emptyList(),
			placeholderValidator.getUnknownPlaceholders(
				"${campaign.name} ${campaign.city} ${campaign.attributes.city} " +
					"${submission.nome} ${submission.table} ${submission.id} " +
						"${form.version} ${site.url}"));

		Assert.assertEquals(
			Arrays.asList(
				"${campaign.estado}", "${submission.cpf}", "${user.password}",
				"${submission.getClass()}", "${campaign}"),
			placeholderValidator.getUnknownPlaceholders(
				"${campaign.estado} ${submission.cpf} ${user.password} " +
					"${submission.getClass()} ${campaign}"));
	}

}
