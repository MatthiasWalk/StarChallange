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
 * The event carries an action regarding a production.
 * 
 * @author Richard Holzeis
 */
public class Event {

	/** The event type */
	private EventType eventType;

	/** The creation time stamp */
	private long creationTime;

	/** The id of corresponding production */
	private String productionId;

	/**
	 * Instantiates an event.
	 * 
	 * @param eventType: the type of the event.
	 * @param productionId: The id of corresponding production
	 */
	public Event(EventType eventType, String productionId) {
		this.creationTime = System.currentTimeMillis();
		this.eventType = eventType;
		this.productionId = productionId;
	}

	/**
	 * Gets the event type.
	 * 
	 * @return the event type
	 */
	public EventType getEventType() {
		return eventType;
	}

	/**
	 * Sets the event type.
	 * 
	 * @param eventType: the event type.
	 */
	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	/**
	 * Gets the creation time stamp.
	 * 
	 * @return the creation time stamp.
	 */
	public long getCreationTime() {
		return creationTime;
	}

	/**
	 * Sets the creation time stamp.
	 * 
	 * @param creationTime: the creation time stamp.
	 */
	public void setCreationTime(long creationTime) {
		this.creationTime = creationTime;
	}

	/**
	 * Gets the production id.
	 * 
	 * @return the production id.
	 */
	public String getProductionId() {
		return productionId;
	}

	/**
	 * Sets the production id.
	 * 
	 * @param productionId: the production id.
	 */
	public void setProductionId(String productionId) {
		this.productionId = productionId;
	}
}
