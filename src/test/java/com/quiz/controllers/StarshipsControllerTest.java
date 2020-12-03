package com.quiz.controllers;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
public class StarshipsControllerTest {

	@Inject
	@Client("/")
	RxHttpClient client;

	@Test
	public void testPeoplePage() {
		HttpRequest<String> request = HttpRequest.GET("/starships");
		String body = client.toBlocking().retrieve(request);

		assertNotNull(body);
		assertTrue(body.contains("<h1>Starwars information - Starships</h1>"));
	}

	@Test
	public void testPeopleSort() {
		HttpRequest<String> request = HttpRequest.GET("/starships?sort=created&dir=desc");
		String body = client.toBlocking().retrieve(request);

		assertNotNull(body);
		assertTrue(body.contains("Created at (DESC)"));
		assertFalse(body.contains("Created at (ASC)"));
	}
}
