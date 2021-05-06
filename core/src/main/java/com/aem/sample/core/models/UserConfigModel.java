package com.aem.sample.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.sample.core.components.UserConfiguration;

@Model(adaptables=Resource.class)
public class UserConfigModel {
	public static final Logger logger = LoggerFactory.getLogger(UserConfigModel.class);
	@OSGiService
	UserConfiguration userConfig;
	
	public String getUserName() {
		return userConfig.getUserName();
	}
	public String getPassword() {
		return userConfig.getPassword();
	}
	public boolean isAdmin() {
		return userConfig.isAdmin();
	}

}
