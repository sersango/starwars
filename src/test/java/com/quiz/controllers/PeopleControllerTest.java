package com.quiz.controllers;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
		assertEquals("Hello World", body);
	}
}
