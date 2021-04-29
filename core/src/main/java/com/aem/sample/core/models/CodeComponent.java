package com.aem.sample.core.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables=SlingHttpServletRequest.class,
defaultInjectionStrategy=DefaultInjectionStrategy.OPTIONAL)
public class CodeComponent {
	@ValueMapValue
	List<String> names;
	
	@ValueMapValue
	String country;
	
	@ValueMapValue
	String pathBrowser;
	
	@ValueMapValue
	@Default(values="{Boolean}false")
	boolean isMarried;
	
	@ValueMapValue
	String fileReference;
	
	@PostConstruct
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
