package com.aem.sample.core.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(adaptables=Resource.class,
defaultInjectionStrategy=DefaultInjectionStrategy.OPTIONAL)
public class MultifieldComponent {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Inject
	List<Author> multi;
	
	public List<Author> getInfo(){
		if(multi!=null) {
			return new ArrayList<Author>(multi);
		}else {
			return Collections.emptyList();
		}
	}
	@Inject
	String fullName;
	
	@Inject
	@Default(values="default")
	String dateOfBirth;
	
	@Inject
	@Default(values="default")
	String password;
	
	@Inject
	@Default(values="default")
	String married;
	
	@Inject
	@Default(values="default")
	String color;
	
	@Inject
	@Default(values="default")
	String experience;
	
	@Inject
	@Default(values="default")
	String pathBrowser;
	
	@Inject
	@Default(values="default")
	String country;
	
	public String getFullName() {
		return fullName;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public String getMarried() {
		return married;
	}
	public String getColor() {
		return color;
	}
	public String getExperience() {
		return experience;
	}
	public String getPathBrowser() {
		return pathBrowser;
	}
	public String getCountry() {
		return country;
	}
	
	@PostConstruct
	public void init() {
		logger.info(fullName);
	}
}
