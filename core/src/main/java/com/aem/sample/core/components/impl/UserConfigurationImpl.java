package com.aem.sample.core.components.impl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

import com.aem.sample.core.components.UserConfiguration;
import com.aem.sample.core.config.UserConfig;

@Component(service=UserConfiguration.class, immediate = true)
@Designate(ocd=UserConfig.class)
public class UserConfigurationImpl implements UserConfiguration {
	
	private String userName;
	private String password;
	private boolean isAdmin;
	
	@Activate
	public void activate(UserConfig userConfig) {
		userName = userConfig.getUserName();
		password = userConfig.getPassword();
		isAdmin = userConfig.isAdmin();
	}
	
	@Override
	public String getUserName() {
		return userName;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public boolean isAdmin() {
		return isAdmin;
	}
	
	

}

