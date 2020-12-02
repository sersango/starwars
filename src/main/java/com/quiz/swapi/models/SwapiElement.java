package com.quiz.swapi.models;

import java.time.Instant;
import java.util.Date;
import java.util.Map;

public abstract class SwapiElement {

	protected String name;
	protected Date created;

	public SwapiElement(Map<String, Object> map) {
		super();
		Instant instant = Instant.parse((String)map.get("created"));
		this.created = Date.from(instant);
		this.name = (String)map.get("name");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
}
