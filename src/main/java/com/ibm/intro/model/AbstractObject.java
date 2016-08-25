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
 * Represent an abstract object including general object attributes.
 * 
 * @author Richard Holzeis
 *
 */
public abstract class AbstractObject implements Cloneable {

	/** the unique id, set if the object is stored */
	private String id;

	/** the state of the entity (created, read, updated, deleted) */
	private EntityState entityState;

	/** the last change date of the object */
	private Long changeDate;

	/**
	 * Instantiates a default object.
	 */
	protected AbstractObject() {
		entityState = EntityState.CREATED;
	}

	/**
	 * Instantiates a clone of the source object.
	 * 
	 * @param source: the source object to be cloned.
	 */
	protected AbstractObject(AbstractObject source) {
		this.id = source.getId();
		this.entityState = source.getEntityState();
		this.changeDate = source.getChangeDate();
	}

	/**
	 * Gets the unique id.
	 * 
	 * @return: the unique id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the unique id.
	 * 
	 * @param id: the unique id.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the entity state.
	 * 
	 * @return the entity state.
	 */
	public EntityState getEntityState() {
		return entityState;
	}

	/**
	 * Sets the entity state.
	 * 
	 * @param entityState: the entity state.
	 */
	public void setEntityState(EntityState entityState) {
		this.entityState = entityState;
	}

	/**
	 * Gets the change date.
	 * 
	 * @return the change date.
	 */
	public Long getChangeDate() {
		return changeDate;
	}

	/**
	 * Sets the change date.
	 * 
	 * @param changeDate: the change date.
	 */
	public void setChangeDate(Long changeDate) {
		this.changeDate = changeDate;
	}

	/**
	 * Every abstract object needs to be cloneable.
	 */
	@Override
	public abstract Object clone();

	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractObject other = (AbstractObject) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}