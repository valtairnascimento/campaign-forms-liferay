package io.github.valtairnascimento.campaignforms.test.util;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.nio.charset.StandardCharsets;

import java.time.Duration;

import java.util.Base64;

public class ApiTestUtil {
	public static final String ADMIN_PATH = "/o/campaign-forms-admin/v1.0";

	public static final String DELIVERY_PATH = "/o/campaign-forms/v1.0";

	public static Response delete(String path, String login, String password)
		throws Exception {
		return _send(
			_builder(
				path, login, password
			).DELETE());
	}

	public static Response get(String path, String login, String password)
		throws Exception {
		return _send(
			_builder(
				path, login, password
			).GET());
	}

	public static Response post(
			String path, String body, String login, String password,
			String... headers)
		throws Exception {
		HttpRequest.Builder builder = _builder(
			path, login, password
		).header(
			"Content-Type", "application/json"
		).POST(
			HttpRequest.BodyPublishers.ofString(
				(body == null) ? "{}" : body, StandardCharsets.UTF_8)
		);

		for (int i = 0; i < headers.length; i += 2) {
			builder.header(headers[i], headers[i + 1]);
		}

		return _send(builder);
	}

	public static Response put(
			String path, String body, String login, String password)
		throws Exception {
		return _send(
			_builder(
				path, login, password
			).header(
				"Content-Type", "application/json"
			).PUT(
				HttpRequest.BodyPublishers.ofString(
					body, StandardCharsets.UTF_8)
			));
	}

	public static class Response {
		public JSONObject getJSONObject() throws Exception {
			return JSONFactoryUtil.createJSONObject(_body);
		}

		public String getBody() {
			return _body;
		}

		public int getStatus() {
			return _status;
		}

		@Override
		public String toString() {
			return _status + " " + _body;
		}

		private Response(int status, String body) {
			_status = status;
			_body = body;
		}

		private final String _body;
		private final int _status;
	}

	private static HttpRequest.Builder _builder(
		String path, String login, String password) {
		HttpRequest.Builder builder = HttpRequest.newBuilder(
			URI.create(_getPortalURL() + path)
		).header(
			"Accept", "application/json"
		).header(
			"Accept-Language", "pt-BR"
		).timeout(
			Duration.ofSeconds(60)
		);

		if (login != null) {
			String credentials = login + ":" + password;

			builder.header(
				"Authorization",
				"Basic " +
					Base64.getEncoder(
					).encodeToString(
						credentials.getBytes(StandardCharsets.UTF_8)
					));
		}

		return builder;
	}

	private static String _getPortalURL() {
		return System.getProperty(
			"campaignforms.test.portal.url", "http://localhost:8080");
	}

	private static Response _send(HttpRequest.Builder builder)
		throws Exception {
		HttpResponse<String> httpResponse = _httpClient.send(
			builder.build(),
			HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

		return new Response(httpResponse.statusCode(), httpResponse.body());
	}

	private static final HttpClient _httpClient = HttpClient.newBuilder(
	).connectTimeout(
		Duration.ofSeconds(30)
	).build();
}
