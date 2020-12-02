package com.quiz.controllers;

import com.quiz.swapi.models.SwapiElement;
import com.quiz.swapi.services.PeopleService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.View;

import javax.validation.constraints.Pattern;
import java.util.*;

@Controller("/people")
public class PeopleController {

	public static final String PEOPLE = "people";
	public static final String DIRECTION = "dir";
	public static final String SORTED_BY = "sortedBy";
	private final PeopleService peopleService;

	public PeopleController(PeopleService peopleService) {
		this.peopleService = peopleService;
	}

	@View("people")
	@Get
	public Map<String, Object> index(
			@Pattern(regexp = "name|created") Optional<String> sort,
			@Pattern(regexp = "asc|desc") Optional<String> dir) {
		Map<String, Object> model = new HashMap<>();
		List<SwapiElement> peopleList;
		if (peopleService.getList().isEmpty()) {
			peopleService.retrieveAll();
		}
		if (sort.isPresent() && dir.isPresent()) {
			peopleList = peopleService.sortBy(sort.get(), dir.get());
			model.put(DIRECTION, dir.get());
			model.put(SORTED_BY, sort.get());
		} else {
			peopleList = peopleService.getList();
		}
		model.put(PEOPLE, peopleList);
		return model;
	}

}
