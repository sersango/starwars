package com.quiz.controllers;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.View;

@Controller
class HomeController {

	@View("home")
	@Get
	public HttpResponse index() {
		return HttpResponse.ok();
	}

}
