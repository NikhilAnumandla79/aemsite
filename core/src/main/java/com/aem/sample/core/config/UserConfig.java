package com.aem.sample.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "User Configuration", description = "Configure the user Details")
public @interface UserConfig{
	
	@AttributeDefinition(name="userName",description="User Name",type=AttributeType.STRING)
	String getUserName() default "Default username";
	
	@AttributeDefinition(name="password", description="User Password", type = AttributeType.PASSWORD)
	String getPassword() default "";
	
	@AttributeDefinition(name="isAdmin", description = "Is the Admin User or not", type = AttributeType.BOOLEAN)
	boolean isAdmin() default false;  
	
}
