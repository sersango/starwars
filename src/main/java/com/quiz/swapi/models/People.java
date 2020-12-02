package com.quiz.swapi.models;

import java.util.Map;

public class People extends SwapiElement {

	private String height;
	private String mass;

	public People(Map<String, Object> peopleMap) {
		super(peopleMap);
		this.height = (String) peopleMap.get("height");
		this.mass = (String) peopleMap.get("mass");
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getMass() {
		return mass;
	}

	public void setMass(String mass) {
		this.mass = mass;
	}
}
