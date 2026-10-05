package io.github.valtairnascimento.campaignforms.internal.notification;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Assert;
import org.junit.Test;

public class NotificationTemplateRendererTest {
	@Test
	public void testIsEmailAddress() {
		Assert.assertTrue(
			NotificationTemplateRenderer.isEmailAddress(
				"rh@example.com"));
		Assert.assertTrue(
			NotificationTemplateRenderer.isEmailAddress("ana.lima+feira@example.net"));
		Assert.assertFalse(NotificationTemplateRenderer.isEmailAddress(null));
		Assert.assertFalse(NotificationTemplateRenderer.isEmailAddress("rh@"));
		Assert.assertFalse(
			NotificationTemplateRenderer.isEmailAddress("rh@localhost"));
		Assert.assertFalse(
			NotificationTemplateRenderer.isEmailAddress(
				"\"Ana\" <ana@example.org>"));
		Assert.assertFalse(
			NotificationTemplateRenderer.isEmailAddress(
				"ana@example.org\r\nBcc: x@y.com"));
	}

	@Test
	public void testRenderHTMLEscapesTemplateAndAnswers() {
		NotificationData notificationData = _createNotificationData();

		String html = NotificationTemplateRenderer.renderHTML(
			"<b>Olá</b> ${submission.nome}\nTabela:\n${submission.table}",
			notificationData);

		Assert.assertTrue(
			html, html.contains("&lt;b&gt;Olá&lt;/b&gt; Maria &lt;script&gt;"));
		Assert.assertFalse(html, html.contains("<script>"));
		Assert.assertTrue(html, html.contains("<br>Tabela:<br><table"));
		Assert.assertTrue(html, html.contains(">Nome</th>"));
		Assert.assertTrue(html, html.contains(">Inovação, Gestão</td>"));
		Assert.assertTrue(html, html.startsWith("<!DOCTYPE html>"));
	}

	@Test
	public void testRenderTextIsOneLine() {
		NotificationData notificationData = _createNotificationData();

		notificationData.put("submission.cidade", "Curitiba\r\nBcc: x@y.com");

		Assert.assertEquals(
			"Nova inscrição de Maria <script> (Curitiba Bcc: x@y.com) em " +
				"Londrina",
			NotificationTemplateRenderer.renderText(
				"Nova inscrição de ${submission.nome}\n" +
					"(${submission.cidade}) em ${campaign.attributes.city}" +
						"${submission.table}",
				notificationData));
		Assert.assertEquals(
			"", NotificationTemplateRenderer.renderText(null, notificationData));
		Assert.assertEquals(
			"Desconhecido: .",
			NotificationTemplateRenderer.renderText(
				"Desconhecido: ${submission.outro}.", notificationData));
	}

	@Test
	public void testResolveAddresses() {
		NotificationData notificationData = _createNotificationData();

		notificationData.addFieldValue("email", "maria@example.org");
		notificationData.addFieldValue("emailInvalido", "não é e-mail");

		Assert.assertEquals(
			Arrays.asList(
				"rh@example.com", "maria@example.org",
				"eventos@example.org"),
			NotificationTemplateRenderer.resolveAddresses(
				"rh@example.com; ${submission.email}, RH@example.com " +
					"eventos@example.org ${submission.emailInvalido} " +
						"${campaign.name} invalido@",
				notificationData));
		Assert.assertEquals(
			Collections.emptyList(),
			NotificationTemplateRenderer.resolveAddresses(
				null, notificationData));
	}

	private NotificationData _createNotificationData() {
		NotificationData notificationData = new NotificationData();

		notificationData.put("campaign.city", "Londrina");
		notificationData.put("campaign.name", "Feira <2026>");
		notificationData.put("submission.nome", "Maria <script>");

		notificationData.addTableRow("Nome", "Maria <script>");
		notificationData.addTableRow("Interesses", "Inovação, Gestão");

		return notificationData;
	}

}
