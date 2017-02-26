//@formatter:off
/**
 *  $Id: ActivitiMap.java 197671 2014-09-19 13:25:37Z velasca $ 
 *       . * .
 *     * RRRR  *    Copyright (c) 2014 OHIM: Office for Harmonization
 *   .   RR  R   .  in the Internal Market (trade marks and designs)
 *   *   RRR     *
 *    .  RR RR  .   ALL RIGHTS RESERVED
 *     * . _ . *
 */
//@formatter:on
package com.solutions.mappings;

import java.util.HashMap;

/**
 * The Class ActivitiMap.
 */
public class ActivitiMap extends HashMap<String, Object> {

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 2909079797664614797L;

	/**
	 * Put variable.
	 * 
	 * @param key the key
	 * @param value the value
	 * @return the activiti map
	 */
	public ActivitiMap putVariable(String key, Object value) {
		super.put(key, value);
		return this;
	}

}
