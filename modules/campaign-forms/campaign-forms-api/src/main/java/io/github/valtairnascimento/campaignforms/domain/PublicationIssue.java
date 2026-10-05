package io.github.valtairnascimento.campaignforms.domain;

import java.io.Serializable;

import java.util.Objects;

public class PublicationIssue implements Serializable {
	public PublicationIssue(String code, String target) {
		_code = code;
		_target = (target == null) ? "" : target;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof PublicationIssue)) {
			return false;
		}

		PublicationIssue publicationIssue = (PublicationIssue)object;

		return Objects.equals(_code, publicationIssue._code) &&
			   Objects.equals(_target, publicationIssue._target);
	}

	public String getCode() {
		return _code;
	}

	public String getTarget() {
		return _target;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_code, _target);
	}

	@Override
	public String toString() {
		if (_target.isEmpty()) {
			return _code;
		}

		return _code + "(" + _target + ")";
	}

	private static final long serialVersionUID = 1L;

	private final String _code;
	private final String _target;
}
