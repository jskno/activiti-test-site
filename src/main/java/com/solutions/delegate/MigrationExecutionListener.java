//@formatter:off
/**
 *  $Id$$
 *       . * .
 *     * RRRR  *    Copyright (c) 2017 EUIPO: European Union Intellectual
 *   .   RR  R   .  Property Office (trade marks and designs)
 *   *   RRR     *
 *    .  RR RR  .   ALL RIGHTS RESERVED
 *     * . _ . *
 */
//@formatter:on
package com.solutions.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.VariableInstance;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Migration CallActivity listener. Work in progress.
 * @author herrpvi
 * 
 */
public class MigrationExecutionListener implements ExecutionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 165669914027484082L;
	
	private static final Logger LOG = LoggerFactory.getLogger(MigrationExecutionListener.class);
	
	@Override
	public void notify(DelegateExecution execution) {
		LOG.debug("Migration task created: " + execution.getId());
		ExecutionEntity actualExecution = (ExecutionEntity) execution;
		List<Execution> executions = actualExecution.getEngineServices().getRuntimeService().createExecutionQuery().list();
		List<ProcessInstance> instances = actualExecution.getEngineServices().getRuntimeService().createProcessInstanceQuery().list();
		for(Execution executionF : executions) {
			Map<String, VariableInstance> map =  execution.getVariableInstances();
		}

//		RuntimeService runtimeService = execution.getEngineServices().getRuntimeService();
//		runtimeService.startProcessInstanceByKey("interpartes_create_recordal", execution.getVariables());
//		instances =runtimeService.createProcessInstanceQuery().list();

	}
	
}
