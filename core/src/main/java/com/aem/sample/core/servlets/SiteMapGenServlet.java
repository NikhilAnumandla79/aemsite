package com.aem.sample.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.aem.sample.core.components.SiteMapGen;

@Component(service= Servlet.class)
@SlingServletResourceTypes (
		resourceTypes ="AemSite/components/structure/page",
		methods = {HttpConstants.METHOD_GET},
		selectors ="sitemap",
		extensions = "xml"
		)
public class SiteMapGenServlet extends SlingSafeMethodsServlet{
	
	
	@Reference
	SiteMapGen siteMapGen;

	@Override
	protected void doGet(SlingHttpServletRequest req, SlingHttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("application/xml");
		siteMapGen.generateSiteMap(req.getResource(), res.getWriter());
	}

}
