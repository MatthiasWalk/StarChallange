/**
 * star-challenge
 *
 * (C) Copyright 2016 IBM Corporation
 * All rights reserved
 *
 * Creation date: 03.08.2016
 */
package com.ibm.intro.model;

/**
 * An enumeration of the status of a production.
 * 
 * @author Richard Holzeis
 */
public enum Status {

	/** The created. */
	CREATED (1),
	
	/** The in production. */
	INPRODUCTION (2),
	
	/** The completed. */
	COMPLETED (3);
	
	/**  defines the order of a status. */
	int order;
	
	/**
	 * Instantiates a new status.
	 *
	 * @param quality the quality
	 */
	private Status(int quality) {
		this.order = quality;
	}
	
	/**
	 *  
	 * Determines if the current status is before the given status.
	 *
	 * @param status the status
	 * @return true if the current status is before the given status.
	 */
	public boolean isBefore(Status status) {
		if (this.order < status.getOrder()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Gets the order.
	 * @return the order.
	 */
	public int getOrder() {
		return this.order;
	}
}
