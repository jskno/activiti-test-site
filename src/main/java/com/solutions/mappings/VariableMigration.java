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
package com.solutions.mappings;

/**
 * The Enum Variable for the migration.
 */
public enum VariableMigration {
	//@formatter:off
	// Variable to indicate that the workflow is a special migration by subworkflow
	MIGRATION_BY_SUBWORKFLOW_FLAG("migrationSubworkflow"),
	// Variable to indicate the process instance id of migration workflow manager
	MIGRATION_PROCESS_INSTANCE_ID("migrationProcessInstanceId");
	//@formatter:on


	/**
	 * The value
	 */
	private String value;

	/**
	 * The value constructor.
	 *
	 * @param value
	 */
	private VariableMigration(final String value) {
		this.value = value;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public String getValue() {
		return this.value;
	}
}
