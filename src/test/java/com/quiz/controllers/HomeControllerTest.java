package com.quiz.controllers;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
public class HomeControllerTest {

	@Inject
	@Client("/")
	RxHttpClient client;

	@Test
	public void testHomePage() {
		HttpRequest<String> request = HttpRequest.GET("/");
		String body = client.toBlocking().retrieve(request);

		assertNotNull(body);
		assertTrue(body.contains("<title>Home</title>"));
	}
}
