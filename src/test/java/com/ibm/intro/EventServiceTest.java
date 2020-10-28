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
import com.ibm.intro.model.*;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author Richard Holzeis
 */
public class EventServiceTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testProcessEvent() throws DataStoreException, InterruptedException {
        var productionService = new ProductionService();
        var eventService = new EventService();

        var production = new Production(ProductionType.LINEHAUL, 1L);
        production = productionService.create(production);

        var event = new Event(EventType.EXIT, production.getId());

        eventService.process(event);
        Thread.sleep(20);

        Assert.assertEquals(Status.INPRODUCTION, productionService.read(event.getProductionId()).getStatus());
    }
}
