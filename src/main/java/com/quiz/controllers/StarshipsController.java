package com.quiz.controllers;

import com.quiz.swapi.models.SwapiElement;
import com.quiz.swapi.services.StarshipService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.View;

import javax.validation.constraints.Pattern;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller("/starships")
public class StarshipsController {

	public static final String STARSHIPS = "starships";
	public static final String DIRECTION = "dir";
	public static final String SORTED_BY = "sortedBy";
	private final StarshipService starshipService;

	public StarshipsController(StarshipService starshipService) {
		this.starshipService = starshipService;
	}

	@View("starships")
	@Get
	public Map<String, Object> index(
			@Pattern(regexp = "name|created") Optional<String> sort,
			@Pattern(regexp = "asc|desc") Optional<String> dir) {
		Map<String, Object> model = new HashMap<>();
		List<SwapiElement> startshipList;
		if (starshipService.getList().isEmpty()) {
			starshipService.retrieveAll();
		}
		if (sort.isPresent() && dir.isPresent()) {
			startshipList = starshipService.sortBy(sort.get(), dir.get());
			model.put(DIRECTION, dir.get());
			model.put(SORTED_BY, sort.get());
		} else {
			startshipList = starshipService.getList();
		}
		model.put(STARSHIPS, startshipList);
		return model;
	}

}
