package com.pharmacoldtrack.platform.shared.domain.model.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

@Getter
public abstract class DomainEvent extends ApplicationEvent {
    private final String eventType;

    protected DomainEvent(Object source, String eventType) {
        super(source, Clock.systemDefaultZone());
        this.eventType = eventType;
    }
}