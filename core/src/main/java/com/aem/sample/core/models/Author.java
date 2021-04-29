package com.aem.sample.core.models;

public class Author {
	private String fullName;
	private String dateOfBirth;
	private String password;
	private String married;
	private String color;
	private String experience;
	private String pathBrowser;
	private String country;
	

	
	public Author(String fullName, String dateOfBirth, String password, String married, String color, String experience,
			String pathBrowser, String country) {
		super();
		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;
		this.password = password;
		this.married = married;
		this.color = color;
		this.experience = experience;
		this.pathBrowser = pathBrowser;
		this.country = country;
	}
	public String getFullName() {
		System.out.println("full name");
		return fullName;
		
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMarried() {
		return married;
	}
	public void setMarried(String married) {
		this.married = married;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public String getPathBrowser() {
		return pathBrowser;
	}
	public void setPathBrowser(String pathBrowser) {
		this.pathBrowser = pathBrowser;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	
}
