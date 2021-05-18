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
				   "process.label"+" = AemSite Simple Workflow",
				   Constants.SERVICE_VENDOR + "= AemSite",
				   Constants.SERVICE_DESCRIPTION + "= AemSite simple workflow step"
		   })

public class AemSiteWorkflowStep implements WorkflowProcess{
	private static final Logger logger = LoggerFactory.getLogger(AemSiteWorkflowStep.class);
	

	@Override
	public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap processArguments) throws WorkflowException {
		WorkflowData workflowData = workItem.getWorkflowData();
		if(workflowData.getPayloadType().equals("JCR_PATH")) {
			Session session = workflowSession.adaptTo(Session.class);
			String path = workflowData.getPayload().toString()+"/jcr:content";
			try {
				Node node = (Node)session.getItem(path);
				String[] processArgs = processArguments.get("PROCESS_ARGS","string").split(",");
				for(String str : processArgs) {
					String[] property = str.split(":");
					if(node != null) {
						String key = property[0];
						String value = property[1];
						node.setProperty(key, value);
						logger.info(key+" : "+ value);
					}
					
				}
			}catch(RepositoryException e) {
				logger.info(e.getMessage());
			}
			
		}
		
	}

}
