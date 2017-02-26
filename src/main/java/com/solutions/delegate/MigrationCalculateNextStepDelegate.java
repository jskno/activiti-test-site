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
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * This delegate handles the call stack of the regression manager.
 */
public class MigrationCalculateNextStepDelegate implements JavaDelegate {
	
	private static final Logger LOG = LoggerFactory.getLogger(MigrationCalculateNextStepDelegate.class);

	@Override
	public void execute(DelegateExecution execution) {
		
		// Convert comma separated values to a object we can handle easily
		String stack = (String) execution.getVariable(MigrationVariable.MIGRATION_REGRESSION_STACK.getValue());
		String[] stackArray = stack.split(",");
		
		// Set the next "migration point" to the popped element
		execution.setVariable(MigrationVariable.MIGRATION_POINT_AFTER_COMPLETE.getValue(), stackArray[0]);
		
		// Create a new "stack" minus the popped element
		String newStack = String.join(",", Arrays.copyOfRange(stackArray, 1, stackArray.length));
		
		// Sets the new stack or removes it if empty
		if (StringUtils.isEmpty(newStack)) {
			execution.removeVariable(MigrationVariable.MIGRATION_REGRESSION_STACK.getValue());
		} else {
			execution.setVariable(MigrationVariable.MIGRATION_REGRESSION_STACK.getValue(), newStack);
		}
		
		LOG.debug("Next step calculated: " + stackArray[0]);
		
	}

}
