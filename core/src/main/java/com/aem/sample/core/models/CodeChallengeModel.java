package com.aem.sample.core.models;

import java.util.List;

public interface CodeChallengeModel{
	public boolean getBoolean();
	public String getString();
	public List<String> getMultifield();
	public String getOption();
	public void printLog();
	String getTitle();
	String getCountry();
	List<String> getLanguages();
	boolean getBool();
}
