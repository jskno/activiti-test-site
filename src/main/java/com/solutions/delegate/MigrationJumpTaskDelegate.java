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
package com.solutions.delegate;

import com.solutions.mappings.MigrationVariable;
import com.solutions.mappings.Variable;
import com.solutions.mappings.VariableMigration;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This delegate handles the task jumping from migration workflow.
 */
public class MigrationJumpTaskDelegate implements JavaDelegate {
	
	private static final Logger LOG = LoggerFactory.getLogger(MigrationJumpTaskDelegate.class);

	@Autowired
	RuntimeService runtimeService;

	@Override
	public void execute(DelegateExecution execution) {
		
		execution.removeVariable(VariableMigration.MIGRATION_BY_SUBWORKFLOW_FLAG.getValue());

		Map<String, Object> varMap = new HashMap<String, Object>(execution.getVariables());
		varMap.remove(Variable.CUSTOM_EXECUTION.getValue());
		// Do we have a call stack? (for the regression manager)
		if (varMap.containsKey(MigrationVariable.MIGRATION_REGRESSION_STACK.getValue())) {
			LOG.debug("Migration task with regression stack: " + varMap.get(MigrationVariable.MIGRATION_REGRESSION_STACK.getValue()));
			varMap.put(VariableMigration.MIGRATION_BY_SUBWORKFLOW_FLAG.getValue(), Boolean.TRUE);
			varMap.put(VariableMigration.MIGRATION_PROCESS_INSTANCE_ID.getValue(), execution.getProcessInstanceId());
		}

//		varMap.put("interpartes_check_if_recordal_needed_result", Boolean.FALSE);
		List<ProcessInstance> instanceList0 = getProcessEngine().getRuntimeService().createProcessInstanceQuery().list();
		getProcessEngine().getRuntimeService().startProcessInstanceByMessage(execution.getVariable(MigrationVariable.MIGRATION_POINT_AFTER_COMPLETE.getValue()).toString(), varMap);
		List<ProcessInstance> instanceList1 = getProcessEngine().getRuntimeService().createProcessInstanceQuery().list();
	}
	
	/**
	 * Get Activiti process engine statically  
	 * @return
	 */
	public ProcessEngine getProcessEngine() {
		return ProcessEngines.getDefaultProcessEngine();
	}

}
