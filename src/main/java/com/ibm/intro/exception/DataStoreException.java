/**
 * star-challenge
 *
 * (C) Copyright 2016 IBM Corporation
 * All rights reserved
 *
 * Creation date: 03.08.2016
 */
package com.ibm.intro.exception;

/**
 * Wraps exceptions thrown by the data access layer.
 * 
 * @author Richard Holzeis
 */
public class DataStoreException extends Exception {

	/** the serial version uid */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates a DataStoreException with a message
	 * 
	 * @param message: the exception message
	 */
	public DataStoreException(String message) {
		super(message);
	}
}
