package com.ibm.intro;

import com.ibm.intro.model.EventType;
import com.ibm.intro.model.ProductionType;

import java.util.Objects;

/**
 * Data structure to enable the Process map, to both have the ProductionType and the EventType as a key
 */
public class EnumKeyPair { //Could use better naming, but ProductionTypeEventTypeKeyPair doesn't really have the same ring to it

    private ProductionType productionType;
    private EventType eventType;

    EnumKeyPair(ProductionType productionType, EventType eventType) {
        this.productionType = productionType;
        this.eventType = eventType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnumKeyPair that = (EnumKeyPair) o;
        return productionType.equals(that.productionType) &&
                eventType.equals(that.eventType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productionType, eventType);
    }
}
