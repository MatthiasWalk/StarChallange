/**
 * star-challenge
 * <p>
 * (C) Copyright 2016 IBM Corporation
 * All rights reserved
 * <p>
 * Creation date: 03.08.2016
 */
package com.ibm.intro;

import com.ibm.intro.dao.InMemoryDao;
import com.ibm.intro.dao.ProductionDao;
import com.ibm.intro.exception.DataStoreException;
import com.ibm.intro.exception.OptimisticLockingException;
import com.ibm.intro.model.EntityState;
import com.ibm.intro.model.Production;


/**
 * Service for performing CRUD operations on Production Entities on the data store
 * @author Richard Holzeis
 */
public class ProductionService implements CRUDService<Production> {

    private final InMemoryDao<Production> dao;

    public ProductionService() {
        dao = ProductionDao.getInstance();
    }

    /**
     * Creates a new entity
     * @param production the entity to create
     * @return the created entity
     * @throws DataStoreException if there was an underlying issue
     */
    @Override
    public Production create(Production production) throws DataStoreException {
        production.setEntityState(EntityState.CREATED);
        return dao.persist(production);
    }

    /**
     * Returns a stored entity by id
     * @param id the id of the stored entity
     * @return either the stored entity or null
     */
    @Override
    public Production read(String id) {
        return dao.load(id);
    }

    /**
     * Updates a stored entity
     * @param production the entity to update
     * @return the updated entity
     * @throws DataStoreException if there was an underlying issue
     * @throws OptimisticLockingException if the entity has been changed in the meantime
     */
    @Override
    public Production update(Production production) throws DataStoreException, OptimisticLockingException {
        production.setEntityState(EntityState.UPDATED);
        return performSafeWrite(production);
    }

    /**
     * Deletes an entity from the store
     * @param production the entity to delete
     * @throws DataStoreException if there was an underlying issue
     * @throws OptimisticLockingException if the entity has been changed in the meantime
     */
    @Override
    public void delete(Production production) throws DataStoreException, OptimisticLockingException {
        production.setEntityState(EntityState.DELETED);
        performSafeWrite(production);
    }

    /**
     * Handles the safe writing of a production entity
     * @param production the entity to write
     * @return the written entity
     * @throws DataStoreException if there was an underlying issue
     * @throws OptimisticLockingException if the entity has been changed in the meantime
     */
    private Production performSafeWrite(Production production) throws DataStoreException, OptimisticLockingException {
        if (isCleanWrite(production)) {
            dao.lock(production.getId(), 300L); //This should probably be configurable
            try{
                return dao.persist(production);
            }finally {
                dao.release(production.getId());
            }
        } else {
            throw new OptimisticLockingException("Cannot write dirty data");
        }
    }

    /**
     * Determines if an entity is safe to write
     * @param production the entity for which to determine the safety to write of
     * @return if the entity is safe to write
     */
    private boolean isCleanWrite(Production production) {
        Long lastChangedDate = dao.loadLastChangeDate(production.getId());
        return lastChangedDate.equals(production.getChangeDate());
    }
}
