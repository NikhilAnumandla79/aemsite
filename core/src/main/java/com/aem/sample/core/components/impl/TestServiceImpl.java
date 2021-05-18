package com.aem.sample.core.components.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.IOUtils;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.ResourceUtil;
import org.apache.sling.api.resource.ValueMap;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.sample.core.components.TestService;
import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.Rendition;
import com.day.cq.wcm.api.Page;

@Component(service = TestService.class, immediate = true)
public class TestServiceImpl implements TestService {
	final static Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);
	@Reference
	ResourceResolverFactory resolverFactory;
	
	ResourceResolver resolver;
	Resource resource;
	final static String SYSTEM_USER="getResourceResolver";
	@Activate
	public void activate() {
		logger.info("TestService Activated");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(ResourceResolverFactory.SUBSERVICE, (Object) SYSTEM_USER);
		try {
			resolver = resolverFactory.getServiceResourceResolver(map);
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public List<String> readFile(String path) {
		logger.info("readFile method Activated");
		resource = resolver.getResource(path);
		Asset asset = resource.adaptTo(Asset.class);
		Rendition rendition = asset.getOriginal();
//		File file = rendition.adaptTo(File.class);
		InputStream inputStream = rendition.adaptTo(InputStream.class);
//		List<Player> players = new ArrayList<>();
		List<String> lines = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
			String line = "";
			logger.info("readFile method Activated");
			while ((line = br.readLine()) != null) {
//				String[] values = line.split(",");
//				players.add(new Player(values[0], values[1], values[2]));
				logger.info(line);
				lines.add(line);
			}	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}

	@Override
	public String readFileAsString(String path) {
		String result = null;
		try {
			logger.info("TestService method Activated");
			resource = resolver.getResource(path);
			Asset asset = resource.adaptTo(Asset.class);
			Rendition rendition = asset.getOriginal();
			InputStream inputStream = rendition.adaptTo(InputStream.class);

			if (inputStream != null) {

				result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
//				String[] lines = result.split(" ");
//				String data="";
//				for(String line : lines) {
//					data += line+"\n";
//				}
//				return data;
				return result;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("TestService method ended");
		return result;
	}

	@Override
	public List<Player> readFileAsListObj(String pathReference) {
		logger.info("readFile method Activated");
		resource = resolver.getResource(pathReference);
		Asset asset = resource.adaptTo(Asset.class);
		Rendition rendition = asset.getOriginal();
//		File file = rendition.adaptTo(File.class);
		InputStream inputStream = rendition.adaptTo(InputStream.class);
		List<Player> players = new ArrayList<>();
//		List<String> lines = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
			String line = "";
			logger.info("readFile method Activated");
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				players.add(new Player(values[0], values[1], values[2]));
				logger.info(line);
//				lines.add(line);
			}	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return players;
	}

	@Override
	public List<String> getPageInfo(String path) {
		logger.info("map method is called..................");
		List<String> map = new ArrayList<>();
		Resource resource = resolver.getResource(path);
		ValueMap valueMap = resource.adaptTo(ValueMap.class);
		logger.info("valueMap data returned..................");
		for(Entry<String, Object> e : valueMap.entrySet()) {
			map.add(e.getKey()+" : "+ e.getValue().toString());
		}
		logger.info("map data returned..................");
		return map;
	}
	
	
	
	
//	@Override
//	public String readFileAsString(String path) {
//		String result ="";
//		logger.info("TestService method Activated");
//		resource = resolver.getResource(path);
//		File file = resource.adaptTo(File.class);
//		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
//			String line = "";
//			while ((line = br.readLine()) != null) {
//				result += line+"\n";
//			}
//			return result;
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		logger.info("TestService method ended");
//		return "Not Found";
//	}
}
