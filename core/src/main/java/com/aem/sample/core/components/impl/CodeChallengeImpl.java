package com.aem.sample.core.components.impl;

import java.util.List;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.sample.core.components.CodeChallenge;
import com.aem.sample.core.config.DemoConfig;

@Component(service=CodeChallenge.class, immediate = true)
@Designate(ocd=DemoConfig.class)
public class CodeChallengeImpl implements CodeChallenge{
	final static Logger logger = LoggerFactory.getLogger(CodeChallengeImpl.class);
	
	private String title;
	private boolean bool;
	private String[] languages;
	private String country;
	
	@Activate
	public void activate(DemoConfig demoConfig) {
		title=demoConfig.getTitle();
		bool = demoConfig.getBool();
		languages = demoConfig.getLanguages();
		country = demoConfig.getCountry();
		
		logger.info("print Log executed");
		logger.info("============================================");
		logger.info("Boolean : "+bool);
		logger.info("String : "+title);
		int count = 0;
		for(String str : languages) {
			logger.info("Value "+(++count) +" : "+str);
		}
		
		logger.info("Option : "+country);
		logger.info("============================================");
		
	}
	
	@Override
	public void printLog(boolean bool, String string, List<String> multifield, String option) {
		
		logger.info("print Log executed");
		logger.info("============================================");
		logger.info("Boolean : "+bool);
		logger.info("String : "+string);
		int count = 0;
		for(String str : multifield) {
			logger.info("Value "+(++count) +" : "+str);
		}
		
		logger.info("Option : "+option);
		logger.info("============================================");
	}
	
	public String getTitle() {
		
		return title;
	}
	public boolean getBool() {
		return bool;
	}
	public String[] getLanguages() {
		return languages;
	}
	public String getCountry() {
		return country;
	}
	
	
}
