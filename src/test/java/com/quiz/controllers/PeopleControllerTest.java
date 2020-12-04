package com.quiz.controllers;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.DefaultHttpClientConfiguration;
import io.micronaut.http.client.HttpClientConfiguration;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
public class PeopleControllerTest {
	@Inject
	@Client("/")
	RxHttpClient client;

	@Test
	public void testPeople() {
		HttpRequest<String> request = HttpRequest.GET("/people");
		String body = client.toBlocking().retrieve(request);

		assertNotNull(body);
		assertTrue(body.contains("<h1>Starwars information - People</h1>"));
	}
}
