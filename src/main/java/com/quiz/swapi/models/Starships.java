package com.quiz.swapi.models;

import java.util.Map;

public class Starships extends SwapiElement {

	private String length;
	private String crew;
	private String passengers;

	public Starships(Map<String, Object> starshipsMap) {
		super(starshipsMap);
		this.length = (String) starshipsMap.get("length");
		this.crew = (String) starshipsMap.get("crew");
		this.passengers = (String) starshipsMap.get("passengers");
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getCrew() {
		return crew;
	}

	public void setCrew(String crew) {
		this.crew = crew;
	}

	public String getPassengers() {
		return passengers;
	}

	public void setPassengers(String passengers) {
		this.passengers = passengers;
	}
}
