package com.ibm.intro;


import com.ibm.intro.model.Event;
import com.ibm.intro.model.Status;

import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.logging.Logger;


/**
 * Callable for the processing of events
 */
public class EventProcessingCallable implements Callable<Void> {

    private final Event event;
    private final ProductionService productionService;
    private final Logger logger;
    private final HashMap<EnumKeyPair, Status> processMap;

    EventProcessingCallable(Event event, ProductionService productionService, Logger logger, HashMap<EnumKeyPair, Status> processMap) {
        this.event = event;
        this.productionService = productionService;
        this.logger = logger;
        this.processMap = processMap;
    }

    /**
     * Processes an event
     *
     * @return null
     * @throws Exception
     */
    @Override
    public Void call() throws Exception {
        var production = productionService.read(event.getProductionId());

        var resultingStatus = processMap.get(new EnumKeyPair(production.getProductionType(), event.getEventType()));

        if (resultingStatus == null) {
            logger.warning(String.format("The event for the production with id '%s' was invalid", production.getId()));
        } else {
            if (production.getStatus().isBefore(resultingStatus)) {
                production.setStatus(resultingStatus);
                productionService.update(production);
            } else {
                logger.warning("Invalid event, the status can only move forward.");
            }
        }
        return null;
    }
}
