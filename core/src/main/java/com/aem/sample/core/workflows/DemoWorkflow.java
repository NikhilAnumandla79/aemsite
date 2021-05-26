package com.aem.sample.core.workflows;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.osgi.service.component.annotations.Component;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;

@Component(service=WorkflowProcess.class,
		immediate=true,
		property = {
				"process.label"+"= Demo Workflow process"
		})

public class DemoWorkflow implements WorkflowProcess{

	@Override
	public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap processArguments) throws WorkflowException {
		WorkflowData workflowData = workItem.getWorkflowData();
		Session session = workflowSession.adaptTo(Session.class);
		if(workflowData.getPayloadType().equals("JCR_PATH")){
			String path = workflowData.getPayload().toString()+"jcr:content";
			try {
				Node node = (Node) session.getItem(path);
				String fullname = processArguments.get("FULLNAME","string");
				String country = processArguments.get("COUNTRY","string");
				node.setProperty("Full Name",fullname);
				node.setProperty("Country", country);
				
				
				
			} catch (PathNotFoundException e) {
				e.printStackTrace();
			} catch (RepositoryException e) {
				e.printStackTrace();
			}
			
		}
		
		
	}
	
}
