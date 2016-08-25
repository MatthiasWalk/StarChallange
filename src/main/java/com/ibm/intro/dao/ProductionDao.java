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

	/** gets the singleton instance */
	public synchronized static ProductionDao getInstance() {
		if (instance == null) {
			instance = new ProductionDao();
		}
		return instance;
	}
}
