/**
 * star-challenge
 *
 * (C) Copyright 2016 IBM Corporation
 * All rights reserved
 *
 * Creation date: 03.08.2016
 */
package com.ibm.intro.dao;

import com.ibm.intro.model.Production;

/**
 * The ProductionDao is a specialization of the InMemoryDao and is accessed through the singleton pattern.
 * 
 * @author Richard Holzeis
 */
public class ProductionDao extends InMemoryDao<Production> {

	/** the singleton instance */
	private static ProductionDao instance;
	/*
		Bonus question answer:
			the getInstance method needs to be synchronized in order to make the singleton thread-safe.
			What this means is that when the getInstance method is called for the first time, it could be called from different threads.
			Those different threads could then hold different versions of the same singleton, which would make it not a singleton anymore.

			I disagree though that the synchronized keyword is best way to do this, since you will impact performance,
			because every call to the method, not just the first one will be blocking.

			A better way to achieve the same goal cheaper would be to use synchronized inside of the if statement, because then
			it would only be called once at the initialization.

			Thanks for listening to my ted talk.
	 */

	/** gets the singleton instance */
	public synchronized static ProductionDao getInstance() {
		if (instance == null) {
			instance = new ProductionDao();
		}
		return instance;
	}
}
