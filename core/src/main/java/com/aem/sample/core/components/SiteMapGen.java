package com.aem.sample.core.components;

import java.io.Writer;

import org.apache.sling.api.resource.Resource;

public interface SiteMapGen {
	void generateSiteMap(Resource siteResource, Writer writer);
}
