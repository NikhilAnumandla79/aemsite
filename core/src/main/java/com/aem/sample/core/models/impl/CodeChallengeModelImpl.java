package com.aem.sample.core.models.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.sample.core.components.CodeChallenge;
import com.aem.sample.core.models.CodeChallengeModel;

@Model(adaptables=Resource.class,
adapters=CodeChallengeModel.class,
defaultInjectionStrategy=DefaultInjectionStrategy.OPTIONAL)
public class CodeChallengeModelImpl implements CodeChallengeModel{
	final static Logger logger = LoggerFactory.getLogger(CodeChallengeModelImpl.class);
	@OSGiService
	CodeChallenge codeChallenge;
	
	@Inject
	@Default(values="{Boolean}false")
	boolean bool;
	
	@Inject
	@Default(values="Default")
	String string;
	
	@Inject
	List<String> multifield;
	
	@Inject
	@Default(values="not selected")
	String option;

	@Override
	public boolean getBoolean() {
		return bool;
	}

	@Override
	public String getString() {
		return string;
	}

	@Override
	public List<String> getMultifield() {
		if(multifield != null) {
			return new ArrayList<String>(multifield);
		}else {
			return Collections.EMPTY_LIST;
		}
	}

	@Override
	public String getOption() {
		return option;
	}

	
	@Override
	public void printLog() {
		logger.info("print Log called");
		codeChallenge.printLog(bool,string,multifield,option);
		logger.info("print Log exited");
		
	}
	
	@Override
	public String getTitle() {
		return codeChallenge.getTitle();
	}

	@Override
	public String getCountry() {
		return codeChallenge.getCountry();
	}

	@Override
	public List<String> getLanguages() {
		return Arrays.asList(codeChallenge.getLanguages());
	}

	@Override
	public boolean getBool() {
		return codeChallenge.getBool();
	}

}
