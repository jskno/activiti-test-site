package com.solutions.mappings;
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


public enum MigrationVariable {
	//@formatter:off
	
	MIGRATION_POINT_AFTER_COMPLETE("migrationPointAfterComplete"),
	MIGRATION_TASK_TYPE("migrationTaskType"),
	MIGRATION_FORM_KEY("migrationFormKey"),
	MIGRATION_TIME_LIMIT("migrationTimelimit"),
	MIGRATION_REGRESSION_STACK("migrationRegressionStack"),
	MIGRATION_DEADLINE("migrationDeadline"),
	MIGRATION_POINT_TRIGGER_BY("migrationPointTrigger");
	
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
	private MigrationVariable(final String value) {
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
