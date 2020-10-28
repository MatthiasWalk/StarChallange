/**
 * star-challenge
 *
 * (C) Copyright 2016 IBM Corporation
 * All rights reserved
 *
 * Creation date: 03.08.2016
 */
package com.ibm.intro;

import com.ibm.intro.exception.DataStoreException;
import com.ibm.intro.exception.OptimisticLockingException;
import com.ibm.intro.model.AbstractObject;

import javax.xml.crypto.Data;

/**
 * @author Richard Holzeis
 * @param <O> the business object
 */
public interface CRUDService<O extends AbstractObject> {

	public O create(O abstractObject) throws DataStoreException;

	public O read(String id);

	public O update(O abstractObject) throws DataStoreException, OptimisticLockingException;

	public void delete(O abstractObhect) throws DataStoreException, OptimisticLockingException;

}
