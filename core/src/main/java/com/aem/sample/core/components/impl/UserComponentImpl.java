package com.aem.sample.core.components.impl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.sample.core.components.UserComponent;


@Component(service=UserComponent.class,immediate=true)
public class UserComponentImpl implements UserComponent {
	private static final Logger logger = LoggerFactory.getLogger(UserComponentImpl.class);
	
	@Activate
	public void activate() {
		logger.info("service is activated");
	}
	
	
	public String getUserName(){
		return "Nikhil Anumandla";
	}
}
