package com.tutorial.mysql;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "user")
public class TableCreated {
	
	@Id
	private Long id;
	private String name;
	
	public TableCreated() {
		super();
	}
	public TableCreated(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
