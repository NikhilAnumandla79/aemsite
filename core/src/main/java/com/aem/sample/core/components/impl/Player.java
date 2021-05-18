package com.aem.sample.core.components.impl;

public class Player {
	private String name;
	private String country;
	private String roll;
	
	public Player() {
		// TODO Auto-generated constructor stub
	}
	public Player(String name, String country, String roll) {
		this.name = name;
		this.country = country;
		this.roll = roll;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getRoll() {
		return roll;
	}
	public void setRoll(String roll) {
		this.roll = roll;
	}
	
}
