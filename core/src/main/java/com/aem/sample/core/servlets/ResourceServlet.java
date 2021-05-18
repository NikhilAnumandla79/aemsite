package com.aem.sample.core.servlets;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Component(service = Servlet.class)
@SlingServletPaths(
		value = { "/bin/resource" }
		)
public class ResourceServlet extends SlingSafeMethodsServlet {
	@Reference
	ResourceResolverFactory resolverFactory;
	
	@Override
	public void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse res) {
		Map<String, Object> map = new HashMap<String, Object>();
	
		map.put(ResourceResolverFactory.SUBSERVICE,(Object)"resourcevar");
		try {
			ResourceResolver resolver = resolverFactory.getServiceResourceResolver(map);
			Resource resource = resolver.getResource("/content/AemSite/en/firstpage/jcr:content/root/responsivegrid_1230233903/simpletext");
			String path = resource.getPath();
			Page page = (Page) resolver.adaptTo(PageManager.class).getPage(path);
			ValueMap property = resource.adaptTo(ValueMap.class);
			for(Entry<String, Object> e : property.entrySet()) {
			    String key = e.getKey();
			    Object value = e.getValue();
			    res.getWriter().write(key+" : "+value+"\n");
			}
			res.getWriter().write(path+"\n");
			res.getWriter().write(resource.toString()+"\n");
			res.getWriter().write(page.getVanityUrl());
		} catch (Exception e) {
			
		}
		
	}
	
}
