package com.aem.sample.core.components;

import java.util.List;

public interface CodeChallenge{

	void printLog(boolean bool, String string, List<String> multifield, String option);
	String getTitle();
	String getCountry();
	String[] getLanguages();
	boolean getBool();
}
