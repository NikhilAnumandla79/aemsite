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
public class SingleMultifield {
	@ValueMapValue
	List<String> names;
	
	@ValueMapValue
	@Default(values="DemoList")
	String listName;
	
	
	@PostConstruct
	public String getListName() {
		return listName;
	}
	
	public List<String> getNames() {
		if(names != null) {
			return new ArrayList<String>(names);
		}else {
			return Collections.EMPTY_LIST;
		}
		
	}
}
