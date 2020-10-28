package com.ibm.intro;

import com.ibm.intro.model.Status;

import java.util.HashMap;

import static com.ibm.intro.model.EventType.*;
import static com.ibm.intro.model.ProductionType.*;
import static com.ibm.intro.model.Status.COMPLETED;
import static com.ibm.intro.model.Status.INPRODUCTION;


/**
 * This class is used to provide a singleton of the map in which the different valid Event/Production type combinations are stored
 */
abstract class ProcessMap {

    private static HashMap<EnumKeyPair, Status> instance;

    static HashMap<EnumKeyPair, Status> getInstance() {
        if (instance == null) {
            instance = new HashMap<>();
            populateMap();
        }
        return instance;
    }

    private static void populateMap() {
        instance.put(new EnumKeyPair(COLLECTION, COLLECTED), INPRODUCTION);
        instance.put(new EnumKeyPair(COLLECTION, ENTRY), COMPLETED);

        instance.put(new EnumKeyPair(LINEHAUL, ENTRY), COMPLETED);
        instance.put(new EnumKeyPair(LINEHAUL, EXIT), INPRODUCTION);

        instance.put(new EnumKeyPair(DELIVERY, EXIT), INPRODUCTION);
        instance.put(new EnumKeyPair(DELIVERY, DELIVERED), COMPLETED);
    }
}
