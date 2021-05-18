package com.aem.sample.core.components;

import java.util.List;
import java.util.Map;

import com.aem.sample.core.components.impl.Player;
import com.day.cq.wcm.api.Page;

public interface TestService {
	List<String> readFile(String path);
	
	String readFileAsString(String path);

	List<Player> readFileAsListObj(String pathReference);

	List<String> getPageInfo(String path);
}
