package com.aem.sample.core.models.impl;


import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.RequestAttribute;
import org.apache.sling.models.annotations.injectorspecific.ResourcePath;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.aem.sample.core.models.SimpleText;
import com.day.cq.wcm.api.Page;


@Model(adaptables=SlingHttpServletRequest.class,
adapters=SimpleText.class,
defaultInjectionStrategy=DefaultInjectionStrategy.OPTIONAL)
public class SimpleTextImpl implements SimpleText{
//	@SlingObject
//	ResourceResolver resourceResolver;
//	
//	@OSGiService
//	QueryBuilder queryBuilder;
//	@Self
//	SlingHttpServletRequest slingHttpServletRequest;
	
	@ScriptVariable
	Page currentPage;
	
	@RequestAttribute(name="testAttribute")
	private String testAttribute;
	
	@ResourcePath(path="/content/AemSite/en/firstpage")@Via("resource")
	Resource resource;
	
	@ValueMapValue
	@Named("jcr:lastModified")
	String lastModified;
	
	@Inject
	@Via("resource")
	@Default(values="Simple")
	String firstName;
	
	@Inject
	@Via("resource")
	@Default(values="Text")
	String lastName;
	
	@ValueMapValue
	String gender;
	
	@ValueMapValue
	@Default(values="{Boolean}false")
	boolean isMarried;
	
	@PostConstruct
	public String getFirstName() {
		return firstName;
	}
	
	@PostConstruct
	public String getLastName() {
		return lastName;

	}
	
	@PostConstruct
	public String getGender() {
		return gender;

	}
	
	public boolean isMarried() {
		return isMarried;

	}
	
	public String getPageTitle() {
		return currentPage.getTitle();
	}
	public String getTestAttribute() {
		return testAttribute;
	}
	public String getLastModified() {
		return lastModified;
	}
	public String getHomePagePath() {
		return resource.getPath();
	}
}
