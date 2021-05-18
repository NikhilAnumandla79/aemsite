package com.aem.sample.core.workflows;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;

@Component(service = WorkflowProcess.class,
		   immediate = true,
		   property = {
				   "process.label"+" = AemSite UserComp Workflow",
				   Constants.SERVICE_VENDOR + "= AemSite",
				   Constants.SERVICE_DESCRIPTION + "= AemSite simple workflow step"
		   })
public class AemSiteUserCompWorkflow implements WorkflowProcess{
private static final Logger logger = LoggerFactory.getLogger(AemSiteUserCompWorkflow.class);
	

	@Override
	public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap processArguments) throws WorkflowException {
		WorkflowData workflowData = workItem.getWorkflowData();
		if(workflowData.getPayloadType().equals("JCR_PATH")) {
			Session session = workflowSession.adaptTo(Session.class);
			String path = workflowData.getPayload().toString()+"/jcr:content";
			try {
				Node node = (Node)session.getItem(path);
				String fullname = processArguments.get("FULLNAME","string");
				String country = processArguments.get("COUNTRY","string");
				logger.info(fullname+"----"+country+"-----");
					if(node != null) {
						node.setProperty("fullname", fullname);
						node.setProperty("country", country);
					}
					
			}catch(RepositoryException e) {
				logger.info(e.getMessage());
			}
			
		}
		
		
	}
	
	

}
