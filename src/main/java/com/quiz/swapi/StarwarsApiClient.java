package com.quiz.swapi;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;

import javax.inject.Singleton;
import java.util.Map;
import java.util.regex.Pattern;


@Singleton
public class StarwarsApiClient {

	public final Pattern PAGE_PATTERN = Pattern.compile("page=(\\d+)");
	private final RxHttpClient swApiClient;

	public StarwarsApiClient(
			@Client("${micronaut.api.swapi.url}") RxHttpClient httpClient) {
		this.swApiClient = httpClient;
	}

	public Map retrieve(String endpoint, String page) {
		String URI = page == null || page.isEmpty()
				? "/" + endpoint + "/" : "/" + endpoint + "/?page=" + page;
		HttpRequest<String> request = HttpRequest.GET(URI);
		return swApiClient.retrieve(request, Map.class).blockingFirst();
	}
}

