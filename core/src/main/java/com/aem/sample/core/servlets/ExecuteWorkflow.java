package com.aem.sample.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.model.WorkflowModel;

@Component(service=Servlet.class)
@SlingServletPaths(
		value = { "/bin/workflows" }
		)
public class ExecuteWorkflow extends SlingSafeMethodsServlet {
	private static final Logger logger = LoggerFactory.getLogger(ExecuteWorkflow.class);
	
	
	@Override
	public void doGet(final SlingHttpServletRequest req, SlingHttpServletResponse res) {
		String payload=req.getRequestParameter("page").toString();
		String status = "Started Workflow";
		ResourceResolver resourceResolver = req.getResourceResolver();
		
		try {
			WorkflowSession workflowSession = resourceResolver.adaptTo(WorkflowSession.class);
			WorkflowModel workflowModel = workflowSession.getModel("/var/workflow/models/aemsite-page-version");
			WorkflowData workflowData = workflowSession.newWorkflowData("JCR_PATH",payload);
			status = workflowSession.startWorkflow(workflowModel, workflowData).getState();
			
		} catch (WorkflowException e) {
			logger.info(e.getMessage());
		}
		res.setContentType("application/json");
		try {
			res.getWriter().write(status);
		} catch (IOException e) {
			logger.info(e.getMessage());
		}
		
		
	}

}
