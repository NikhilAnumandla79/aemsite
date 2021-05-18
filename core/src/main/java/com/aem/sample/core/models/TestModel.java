package com.aem.sample.core.models;

import java.util.List;

import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.sample.core.components.TestService;
import com.aem.sample.core.components.impl.Player;
import com.day.cq.wcm.api.Page;

@Model(adaptables= {Resource.class,SlingHttpServletRequest.class},
defaultInjectionStrategy=DefaultInjectionStrategy.OPTIONAL)
public class TestModel {
	final static Logger logger = LoggerFactory.getLogger(TestModel.class);
	@OSGiService
	TestService testService;
	
	@ScriptVariable
	Page currentPage;
	
	@Inject
	@Via("resource")
	@Default(values = "AemSite/en/sampleFiles/cricketPlayers.csv")
	String pathReference;
	
	public String getPathReference() {
		return pathReference;
	}
	
//	public List<Player> getFile(){
//		List<Player> players = testService.readFile(pathReference);
//		if(players != null) {
//			logger.info("got here");
//			return players;
//			
//		}else {
//			return Collections.EMPTY_LIST;
//		}
//	}
	public String getPagePath() {
		return currentPage.getPath();
	}
	public String getData(){
		logger.info("TestService data Activated");
		return testService.readFileAsString(pathReference);
	}
	public List<String> getDataAsList(){
		logger.info("getDataAsList Activated");
		return testService.readFile(pathReference);
	}
	public List<Player> getDataAsListObj(){
		logger.info("getDataAsList Activated");
		return testService.readFileAsListObj(pathReference);
	}
	public List<String> getPageInfo(){
		return testService.getPageInfo(getPagePath()+"/jcr:content");

	}
}
