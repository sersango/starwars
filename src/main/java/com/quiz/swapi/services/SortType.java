package com.quiz.swapi.services;

public enum SortType {
	ASCENDING("asc"),
	DESCENDING("desc"),
	NONE("none");

	private final String text;

	SortType(final String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}
}
