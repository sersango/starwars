package com.quiz.swapi.services;

import com.quiz.swapi.StarwarsApiClient;
import com.quiz.swapi.models.Starships;
import io.micronaut.cache.annotation.CacheConfig;
import io.micronaut.cache.annotation.Cacheable;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

@Singleton
@CacheConfig("starships")
public class StarshipService extends Sortable {

	private final StarwarsApiClient swApiClient;

	public StarshipService(StarwarsApiClient swApiClient) {
		super(new ArrayList<>());
		this.swApiClient = swApiClient;
	}

	@Override
	@Cacheable
	public void retrieveAll() {
		Map response;
		String page = "1";
		while (page != null) {
			response = swApiClient.retrieve("starships", page);
			List<Map<String, Object>> starshipsResult =
					(List<Map<String, Object>>)response.get("results");
			starshipsResult.forEach(s -> list.add(new Starships(s)));
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
