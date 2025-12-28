package com.pharmacoldtrack.platform.shipping.domain.model.events;

import com.pharmacoldtrack.platform.shared.domain.model.events.DomainEvent;
import lombok.Getter;

import java.util.UUID;

@Getter
public final class ShipmentCompromisedEvent extends DomainEvent {
    private final UUID trackingId;
    private final Double currentTemperature;
    private final Double minLimit;
    private final Double maxLimit;
    private final String contactEmail;

    public ShipmentCompromisedEvent(Object source, UUID trackingId, Double currentTemperature,
                                    Double minLimit, Double maxLimit, String contactEmail) {
        super(source, "ShipmentCompromisedEvent");
        this.trackingId = trackingId;
        this.currentTemperature = currentTemperature;
        this.minLimit = minLimit;
        this.maxLimit = maxLimit;
        this.contactEmail = contactEmail;
    }
}