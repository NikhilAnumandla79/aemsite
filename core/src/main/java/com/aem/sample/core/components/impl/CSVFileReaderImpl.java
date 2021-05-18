package com.aem.sample.core.components.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.sample.core.components.CSVFileReader;
import com.aem.sample.core.servlets.CSVReader;
import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.Rendition;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.WCMException;

@Component(service = CSVFileReader.class, immediate = true)
public class CSVFileReaderImpl implements CSVFileReader{
	
	@Reference 
	ResourceResolverFactory resolverFactory;
	private final Logger logger = LoggerFactory.getLogger(CSVReader.class);
	ResourceResolver resolver;
	Resource resource;
	private final String SYSTEM_USER = "getResourceResolver";
	@Activate
	public void Activate() {
		Map<String, Object> map = new HashMap<String, Object>();
		logger.info("CSVFileReaderImpl activated");
		map.put(ResourceResolverFactory.SUBSERVICE,(Object)SYSTEM_USER);
		try {
			resolver = resolverFactory.getServiceResourceResolver(map);
		
			
		} catch (LoginException e) {
			e.printStackTrace();
		}
	}
	public int createPagesWithCSVFile() {
		resource = resolver.getResource("/content/dam/AemSite/en/sampleFiles/pages.csv");
		Asset asset = resource.adaptTo(Asset.class);
		Rendition rendition = asset.getOriginal();
		InputStream inputStream = rendition.adaptTo(InputStream.class);
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		
		String line = "";
		List<Page> createdPages = new ArrayList<Page>();
		PageManager pageManager = resolver.adaptTo(PageManager.class);
		try {
			br.readLine();
			while((line = br.readLine())!=null) {
				String[] pageData = line.split(",");
				logger.info(pageData[3].trim()+""+ pageData[0].trim()+""+pageData[1].trim()+""+ pageData[2].trim());
				Page page = pageManager.create(pageData[3].trim(), pageData[0].trim(), pageData[1].trim(), pageData[2].trim());
				createdPages.add(page);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WCMException e) {
			logger.info(e.getMessage());
			logger.info(e.toString());
			
		}
		return createdPages.size();
	}
	
}
