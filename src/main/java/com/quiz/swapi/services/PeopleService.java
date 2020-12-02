package com.quiz.swapi.services;

import com.quiz.swapi.StarwarsApiClient;
import com.quiz.swapi.models.People;
import io.micronaut.cache.annotation.CacheConfig;
import io.micronaut.cache.annotation.Cacheable;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

@Singleton
@CacheConfig("people")
public class PeopleService extends Sortable {

	private final StarwarsApiClient swApiClient;

	public PeopleService(StarwarsApiClient swApiClient) {
		super(new ArrayList<>());
		this.swApiClient = swApiClient;
	}

	@Override
	@Cacheable
	public void retrieveAll() {
		Map response;
		String page = "1";
		while (page != null) {
			response = swApiClient.retrieve("people", page);
			List<Map<String, Object>> peopleResult =
					(List<Map<String, Object>>)response.get("results");
			peopleResult.forEach(p -> list.add(new People(p)));
			String nextPage = (String)response.get("next");
			if (nextPage != null) {
				Matcher matcher = swApiClient.PAGE_PATTERN.matcher(nextPage);
				page = matcher.find() ? matcher.group(1) : null;
			} else {
				page = null;
			}
		}
	}

}
