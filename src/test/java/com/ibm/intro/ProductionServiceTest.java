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
import com.ibm.intro.model.Production;
import com.ibm.intro.model.ProductionType;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author Richard Holzeis
 */
public class ProductionServiceTest {

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test
	public void testCreate() throws DataStoreException {
		var productionService = new ProductionService();
		var production = new Production(ProductionType.COLLECTION, 1L);
		production = productionService.create(production);

		var retrievedProduction = productionService.read(production.getId());

		Assert.assertEquals(production, retrievedProduction);
	}

	@Test
	public void testUpdate() throws DataStoreException, OptimisticLockingException {
		var productionService = new ProductionService();
		var production = new Production(ProductionType.COLLECTION, 1L);
		productionService.create(production);

		production.setProductionType(ProductionType.LINEHAUL);
		productionService.update(production);

		Assert.assertEquals(production, productionService.read(production.getId()));
	}

	@Test
	public void testDelete() throws DataStoreException, OptimisticLockingException {
		var productionService = new ProductionService();
		var production = new Production(ProductionType.COLLECTION, 1L);
		productionService.create(production);

		productionService.delete(production);

		Assert.assertNull(productionService.read(production.getId()));
	}

	@Test
	public void testDirtyWrites() throws DataStoreException, OptimisticLockingException {
		var productionService = new ProductionService();
		var production = new Production(ProductionType.COLLECTION, 1L);

		production = productionService.create(production);

		var production2 = productionService.read(production.getId());
		production2.setProductionType(ProductionType.LINEHAUL);
		productionService.update(production2);

		exception.expect(OptimisticLockingException.class);
		productionService.delete(production);
	}
}
