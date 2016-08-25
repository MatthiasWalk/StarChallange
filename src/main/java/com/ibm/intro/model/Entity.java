/**
 * star-challenge
 *
 * (C) Copyright 2016 IBM Corporation
 * All rights reserved
 *
 * Creation date: 03.08.2016
 */
package com.ibm.intro.model;

import com.ibm.intro.exception.DataStoreException;

/**
 * The entity is a wrapper for an object and maintains the locking information.
 *
 * @author Richard Holzeis
 * @param <O> the
 */
public class Entity<O extends AbstractObject> {

	/** the thread holding the lock on the entity. */
	private Long threadId;
	
	/**  the stored object. */
	private O object;
	
	/**
	 * Instantiates a new entity.
	 *
	 * @param object the object
	 */
	public Entity(O object) {
		this.threadId = Thread.currentThread().getId();
		this.object = object;
	}
	
	/**
	 * In order to prevent unintended updates, the object is always returned as clone.
	 * @return the cloned object.
	 */
	@SuppressWarnings("unchecked")
	public O retrieveObject() {
		return (O) object.clone();
	}

	/**
	 * Retrieves the id of the stored object.
	 * 
	 * @return the id of the stored object.
	 */
	public String retrieveId() {
		return this.object.getId();
	}
	
	/**
	 * Updates the stored object. Note, the thread updating an object is always
	 * holding a lock on the object.
	 *
	 * @param object the object
	 */
	public synchronized void update(O object) throws DataStoreException {
		if (threadId != null && threadId != Thread.currentThread().getId()) {
			throw new DataStoreException("Illegal entity access!");
		}
		this.threadId = Thread.currentThread().getId();
		this.object = object;
	}
	
	/**
	 * Tries to acquire a lock for the current thread.
	 * 
	 * @return true if the lock is granted and false otherwise.
	 */
	public synchronized boolean lock() {
		// #task 1: implement me!
		return false;
	}
	
	/**
	 * Tries to release a lock for the current thread.
	 * 
	 * @return true if the relase succeeded and false otherwise.
	 */
	public synchronized boolean release() {
		// #task 1: implement me!
		return false;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		return this.object.hashCode();
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(Object obj) {
		return this.object.equals(obj);
	}
}
