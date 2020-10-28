/**
 * star-challenge
 * <p>
 * (C) Copyright 2016 IBM Corporation
 * All rights reserved
 * <p>
 * Creation date: 03.08.2016
 */
package com.ibm.intro;

import com.ibm.intro.exception.DataStoreException;
import com.ibm.intro.exception.OptimisticLockingException;
import com.ibm.intro.model.Event;
import com.ibm.intro.model.Status;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

/**
 * Service for processing events
 *
 * @author Richard Holzeis
 */
public class EventService {

    private final ProductionService productionService;
    private final HashMap<EnumKeyPair, Status> processMap;
    private final Logger logger;
    private final ExecutorService executorService;

    public EventService() {
        this.productionService = new ProductionService();
        this.processMap = ProcessMap.getInstance();
        this.logger = Logger.getAnonymousLogger();
        this.executorService = Executors.newCachedThreadPool();
    }


    /**
     * Handles the execution of the processing callables
     * @param event the event to be processed
     */
    public void process(Event event) {
        EventProcessingCallable processingCallable = new EventProcessingCallable(event, productionService, logger, processMap);
        executorService.submit(processingCallable);
    }
}
