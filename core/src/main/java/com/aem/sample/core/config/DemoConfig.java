package com.aem.sample.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.Option;

@ObjectClassDefinition(name ="Demo Config", description="This is a demo config")
public @interface DemoConfig {
	@AttributeDefinition(name = "title", description="Config title", type= AttributeType.STRING)
	String getTitle() default "title";
	
	@AttributeDefinition(name = "bool", description="Config bool", type= AttributeType.BOOLEAN)
	boolean getBool() default false;
	
	@AttributeDefinition(name ="languages", description="Config Multifield", type= AttributeType.STRING)
	String[] getLanguages() default {"hi","de","en"};
	
	@AttributeDefinition(name = "title", description="Config title", type= AttributeType.STRING,
			options = {
					@Option(label="India",value="india"),
					@Option(label="China",value="china"),
					@Option(label="USA",value="usa"),
					@Option(label="Germany",value="germany")
			}
			)
	String getCountry() default "germany";
	
	

}
