package com.pharmacoldtrack.platform.containermonitoring.domain.model.events;

import com.pharmacoldtrack.platform.shared.domain.model.events.DomainEvent;
import lombok.Getter;

import java.util.UUID;

@Getter
public final class TelemetryCreatedEvent extends DomainEvent {
    private final UUID trackingId;
    private final Double temperature;

    public TelemetryCreatedEvent(Object source, UUID trackingId, Double temperature) {
        super(source, "TelemetryCreatedEvent");
        this.trackingId = trackingId;
        this.temperature = temperature;
    }
}