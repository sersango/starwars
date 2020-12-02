package com.quiz.swapi.services;

import com.quiz.swapi.models.SwapiElement;
import io.micronaut.cache.annotation.CachePut;

import java.util.ArrayList;
import java.util.List;

public abstract class Sortable {

	protected SortType sorted;
	protected final List<SwapiElement> list;

	public Sortable(ArrayList<SwapiElement> list) {
		super();
		this.list = list;
		this.sorted = SortType.NONE;
	}

	public abstract void retrieveAll();

	@CachePut(parameters = {"name,direction"})
	public List<SwapiElement> sortBy(String name, String direction) {
		this.sorted = direction.equalsIgnoreCase(SortType.ASCENDING.toString())
				? SortType.ASCENDING : SortType.DESCENDING;
		List<SwapiElement> listSorted = list;
		listSorted.sort((o1, o2) -> {
			int result;
			switch (name) {
				case "created":
					result = direction.equalsIgnoreCase(SortType.ASCENDING.toString())
							? o1.getCreated().compareTo(o2.getCreated())
							: o2.getCreated().compareTo(o1.getCreated());
					break;
				case "name":
				default:
					result = direction.equalsIgnoreCase(SortType.ASCENDING.toString())
							? o1.getName().compareTo(o2.getName())
							: o2.getName().compareTo(o1.getName());
					break;
			}
			return result;
		});
		return listSorted;
	}

	public List<SwapiElement> getList() {
		return list;
	}

	public SortType getSorted() {
		return sorted;
	}

}
