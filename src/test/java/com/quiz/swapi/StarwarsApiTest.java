package com.quiz.swapi;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
public class StarwarsApiTest {

	@Inject
	StarwarsApiClient swApiClient;

	@Test
	public void testStarwarsApiClient() {
		Map responseMap = swApiClient.retrieve("people", "1");

		int count = (int) responseMap.get("count");
		assertEquals(count, 82);
	}

	@Test
	public void testHasPageBeforeAndAfter() {
		Map responseMap = swApiClient.retrieve("starships", "2");

		List<Object> results = (List<Object>) responseMap.get("results");
		String nextPage = (String) responseMap.get("next");
		String previousPage = (String) responseMap.get("previous");

		assertEquals(results.size(), 10);
		assertTrue(nextPage.contains("page=3"));
		assertTrue(previousPage.contains("page=1"));
	}
}
