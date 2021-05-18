package com.aem.sample.core.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;

@Component(service=Servlet.class,
		property= {
				 "sling.servlet.methods="+HttpConstants.METHOD_GET,
				 "sling.servlet.resourceTypes="+"AemSite/components/structure/page",
				 "sling.servlet.selectors=site",
				 "sling.servlet.extensions=json"
		})

public class ResourceTypeServlet extends SlingSafeMethodsServlet{
	private static String RESOURCE_TYPE="AemSite/components/content/simpletext";
	
	@Reference
	private QueryBuilder queryBuilder;
	
	@Override
	protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse res) throws IOException {
		
		res.setContentType("application/json; charset=UTF-8");
		
		final Resource resource = req.getResource();
		
		final ResourceResolver resourceResolver = req.getResourceResolver();
		
		Map<String ,String> map = new HashMap<String, String>();
		map.put("path", resource.getParent().getPath());
		map.put("1_property", "sling:resourceType");
		map.put("1_property.value", RESOURCE_TYPE);
		
		Query query = queryBuilder.createQuery(PredicateGroup.create(map), resourceResolver.adaptTo(Session.class));
		SearchResult result = query.getResult();
		
		final List<Resource> resources = new ArrayList<Resource>();
		
		ResourceResolver leakingResourceResolver = null;
		
		try {
			for(final Hit hit : result.getHits()) {
				if(leakingResourceResolver == null) {
					leakingResourceResolver = hit.getResource().getResourceResolver();
				}
				Resource resur =resourceResolver.getResource(hit.getPath());
				resources.add(resur);
				ValueMap valueMap = resur.adaptTo(ValueMap.class);
				for(Entry<String, Object> e : valueMap.entrySet()) {
					res.getWriter().write(e.getKey()+" : "+e.getValue()+"\n");
				}
				res.getWriter().write("----------------------------------------------------------------------------\n");
			}
			
		}catch(RepositoryException e) {
			
		}finally {
			if(leakingResourceResolver != null) {
				leakingResourceResolver.close();
			}
		}
		
		
		res.getWriter().write("Found Articles : "+Integer.toString(resources.size()));
				
	}
}
