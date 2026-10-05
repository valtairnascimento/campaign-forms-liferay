package io.github.valtairnascimento.campaignforms.domain;

import io.github.valtairnascimento.campaignforms.model.DataFeed;

public class DataFeedCredentials {
	public DataFeedCredentials(DataFeed dataFeed, String token) {
		_dataFeed = dataFeed;
		_token = token;
	}

	public DataFeed getDataFeed() {
		return _dataFeed;
	}

	public String getToken() {
		return _token;
	}

	private final DataFeed _dataFeed;
	private final String _token;
}
