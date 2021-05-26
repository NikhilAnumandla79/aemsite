package com.aem.sample.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.sample.core.components.CSVFileReader;

@Component(service = Servlet.class)
@SlingServletPaths(
		value = {"/bin/csvreaders"}
		)
public class CSVReader extends SlingSafeMethodsServlet{
	private final Logger logger = LoggerFactory.getLogger(CSVReader.class);
	@Reference
	CSVFileReader csvFileReader;
	
	@Override
	public void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse res) throws IOException {
		int totalPagesCreated = csvFileReader.createPagesWithCSVFileDemo();
		logger.info("==================================================");
		logger.info(totalPagesCreated + " pages created successfully");
//		res.sendRedirect("/content/AemSite/en/firstpage.html");
		res.getWriter().write(totalPagesCreated + " pages created successfully");
	}
	
}
