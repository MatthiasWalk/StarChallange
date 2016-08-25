/**
 * star-challenge
 *
 * (C) Copyright 2016 IBM Corporation
 * All rights reserved
 *
 * Creation date: 03.08.2016
 */
package com.ibm.intro.dao;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.ibm.intro.exception.DataStoreException;
import com.ibm.intro.model.Production;
import com.ibm.intro.model.ProductionType;

/**
 * @author Richard Holzeis
 */
public class InMemoryDaoTest {

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test
	public void testLockProduction() throws DataStoreException, InterruptedException, ExecutionException, TimeoutException {
		final Production production = new Production(ProductionType.COLLECTION, 1L);

		ProductionDao.getInstance().persist(production);

		Assert.assertNotNull(production.getId());

		// release lock
		ProductionDao.getInstance().release(production.getId());

		ExecutorService executor = Executors.newFixedThreadPool(1);
		// lock production with a different thread.
		FutureTask<Boolean> lock = new FutureTask<Boolean>(new Callable<Boolean>() {

			@Override
			public Boolean call() throws Exception {
				return ProductionDao.getInstance().lock(production.getId(), 10L);
			}
		});

		// execute new thread.
		executor.execute(lock);

		// production is locked.
		Assert.assertTrue("production could not be locked!", lock.get(10L, TimeUnit.MILLISECONDS));

		// try to acquire lock.
		exception.expect(DataStoreException.class);
		ProductionDao.getInstance().lock(production.getId(), 10L);
	}
	
	@Test
	public void testReleaseProduction() throws DataStoreException, InterruptedException, ExecutionException, TimeoutException {
		final Production production = new Production(ProductionType.COLLECTION, 1L);

		ProductionDao.getInstance().persist(production);

		Assert.assertNotNull(production.getId());

		// release lock
		ProductionDao.getInstance().release(production.getId());

		ExecutorService executor = Executors.newFixedThreadPool(1);
		// lock production with a different thread.
		FutureTask<Boolean> lock = new FutureTask<Boolean>(new Callable<Boolean>() {

			@Override
			public Boolean call() throws Exception {
				return ProductionDao.getInstance().lock(production.getId(), 10L);
			}
		});

		// execute new thread.
		executor.execute(lock);

		// production is locked.
		Assert.assertTrue("production could not be locked!", lock.get(10L, TimeUnit.MILLISECONDS));

		FutureTask<Boolean> release = new FutureTask<Boolean>(new Callable<Boolean>() {

			@Override
			public Boolean call() throws Exception {
				return ProductionDao.getInstance().release(production.getId());
			}
		});

		// execute new thread.
		executor.execute(release);
		
		// production is released.
		Assert.assertTrue("production could not be locked!", release.get(10L, TimeUnit.MILLISECONDS));
		
		// try to acquire lock.
		Assert.assertTrue(ProductionDao.getInstance().lock(production.getId(), 10L));
	}
}