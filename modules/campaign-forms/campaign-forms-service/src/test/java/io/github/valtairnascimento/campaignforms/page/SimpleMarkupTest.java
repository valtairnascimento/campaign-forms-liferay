package io.github.valtairnascimento.campaignforms.page;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Assert;
import org.junit.Test;

public class SimpleMarkupTest {
	@Test
	public void testAllowedURLs() {
		for (String url :
				new String[] {
					"https://www.example.com", "https://a.b/c?d=1#e",
					"/web/acme/obrigado", "#form", "mailto:hr@example.org",
					"tel:+55(41)3271-9000"
				}) {
			Assert.assertTrue(url, SimpleMarkup.isAllowedURL(url));
		}

		for (String url :
				new String[] {
					null, "", "http://inseguro.com", "javascript:alert(1)",
					"JAVASCRIPT:alert(1)", "//evil.example", "data:text/html,x",
					"https://", "https://evil.example\"onmouseover=x",
					"#Form", "/a b", "vbscript:x", "tel:abc"
				}) {
			Assert.assertFalse(String.valueOf(url), SimpleMarkup.isAllowedURL(url));
		}
	}

	@Test
	public void testBlocksAndInline() {
		Assert.assertEquals(
			"<h3>Programação</h3><p>Das <strong>9h</strong> às <em>12h</em>" +
				"<br>no auditório</p><ul><li>palestra</li><li>café</li></ul>" +
					"<ol><li>um</li><li>dois</li></ol>",
			SimpleMarkup.toHtml(
				"## Programação\n\nDas **9h** às *12h*\nno auditório\n\n" +
					"- palestra\n* café\n\n1. um\n2) dois"));

		Assert.assertEquals(
			"<h4>Menor</h4>", SimpleMarkup.toHtml("### Menor"));
		Assert.assertEquals("", SimpleMarkup.toHtml("  \n "));
		Assert.assertEquals("", SimpleMarkup.toHtml(null));

		Assert.assertEquals("<p>2 * 3 * 4</p>", SimpleMarkup.toHtml("2 * 3 * 4"));
	}

	@Test
	public void testEscapesEverything() {
		Assert.assertEquals(
			"<p>&lt;script&gt;alert(&#39;x&#39;)&lt;/script&gt; &amp; " +
				"&quot;aspas&quot;</p>",
			SimpleMarkup.toHtml(
				"<script>alert('x')</script> & \"aspas\""));

		Assert.assertEquals(
			"<p><strong>&lt;img src=x onerror=alert(1)&gt;</strong></p>",
			SimpleMarkup.toHtml("**<img src=x onerror=alert(1)>**"));
	}

	@Test
	public void testLinks() {
		Assert.assertEquals(
			"<p>Veja <a href=\"https://www.example.com/?a=1&amp;b=2\" " +
				"rel=\"noopener noreferrer\">o <strong>site</strong></a> e " +
					"<a href=\"#form\">inscreva-se</a></p>",
			SimpleMarkup.toHtml(
				"Veja [o **site**](https://www.example.com/?a=1&b=2) e " +
					"[inscreva-se](#form)"));

		Assert.assertEquals(
			"<p>[clique](javascript:alert(1))</p>",
			SimpleMarkup.toHtml("[clique](javascript:alert(1))"));

		Assert.assertEquals(
			Arrays.asList("javascript:alert(1", "http://x.com"),
			SimpleMarkup.getInvalidLinks(
				"[a](javascript:alert(1)) [b](http://x.com) [c](#form)"));
		Assert.assertEquals(
			Collections.emptyList(), SimpleMarkup.getInvalidLinks(null));
	}

}
