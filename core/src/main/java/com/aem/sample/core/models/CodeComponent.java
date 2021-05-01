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

@Model(adaptables=Resource.class,
defaultInjectionStrategy=DefaultInjectionStrategy.OPTIONAL)
public class CodeComponent {
	@Inject
	List<String> names;
	
	@Inject
	@Default(values="default")
	String country;
	
	@Inject
	@Default(values="default")
	String pathBrowser;
	
	@Inject
	@Default(values="{Boolean}false")
	boolean isMarried;
	
	@Inject
	@Default(values="default")
	String fileReference;
	
	
	public String getCountry() {
		return country;
	}
	
	public String getPathBrowser(){
		return pathBrowser;
	}
	
	public boolean isMarried() {
		return isMarried;
	}
	
	@PostConstruct
	public String getFileReference() {
		return fileReference;
	}
	public List<String> getNames() {
		if(names != null) {
			return new ArrayList<String>(names);
		}else {
			return Collections.EMPTY_LIST;
		}
		
	}
	
	
}
