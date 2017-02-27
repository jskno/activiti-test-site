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
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This delegate handles the task jumping from migration workflow.
 */
public class MigrationTaskModifierDelegate implements JavaDelegate {

	private static final Logger LOG = LoggerFactory.getLogger(MigrationTaskModifierDelegate.class);

	private static final String TASK_TYPE_CALL_ACTIVITY = "callActivity";

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		initialize();

        List<Task> tasks = null;
		List<Execution> executions = execution.getEngineServices().getRuntimeService().createExecutionQuery().list();
		List<ProcessInstance> instances = execution.getEngineServices().getRuntimeService().createProcessInstanceQuery().list();

		if (execution.getVariable(MigrationVariable.MIGRATION_TASK_TYPE.getValue()).equals(TASK_TYPE_CALL_ACTIVITY)) {
			// CallActivity
			tasks = new ArrayList<Task>();
			List<Task> ctvs = new ArrayList<>();
			for (Task ctv : ctvs) {
				if (execution.getProcessInstanceId().equals(ctv.getProcessInstanceId())) {
					tasks.add(ctv);
				}
			}
		} else {
			// UserTask
			tasks = new ArrayList<>();
		}

		for (Task ctv : tasks) {

			// Process time limit dates
			if (execution.hasVariable(MigrationVariable.MIGRATION_TIME_LIMIT.getValue())) {
				Date timelimit = (Date) execution.getVariable(MigrationVariable.MIGRATION_TIME_LIMIT.getValue());
				LOG.debug("Timeline of set at: " + timelimit);
			}

			// Process deadline dates
			if (execution.hasVariable(MigrationVariable.MIGRATION_DEADLINE.getValue())) {
				Date deadline = (Date) execution.getVariable(MigrationVariable.MIGRATION_DEADLINE.getValue());
				LOG.debug("Deadline of set at: " + deadline);
			}
		}
		executions = execution.getEngineServices().getRuntimeService().createExecutionQuery().list();
		instances = execution.getEngineServices().getRuntimeService().createProcessInstanceQuery().list();

	}

	/**
	 * Make Spring initialize autowired members
	 */
	public void initialize() {
		SpringProcessEngineConfiguration config = (SpringProcessEngineConfiguration) Context.getProcessEngineConfiguration();
		config.getApplicationContext().getAutowireCapableBeanFactory().autowireBean(this);
	}

}
