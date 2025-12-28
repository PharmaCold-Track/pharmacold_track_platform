package com.pharmacoldtrack.platform.shipping.application.internal.eventhandlers;

import com.pharmacoldtrack.platform.containermonitoring.domain.model.events.TelemetryCreatedEvent;
import com.pharmacoldtrack.platform.shipping.domain.model.events.ShipmentCompromisedEvent;
import com.pharmacoldtrack.platform.shipping.infrastructure.persistence.jpa.repositories.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShipmentTelemetryEventHandler {

    private final ShipmentRepository shipmentRepository;
    private final ApplicationEventPublisher eventPublisher;

    @EventListener
    public void on(TelemetryCreatedEvent event) {
        shipmentRepository.findByTrackingId(event.getTrackingId()).ifPresent(shipment -> {
            if (event.getTemperature() < shipment.getMinTemperature() ||
                    event.getTemperature() > shipment.getMaxTemperature()) {

                shipment.compromise("Temp violation: " + event.getTemperature());
                shipmentRepository.save(shipment);

                eventPublisher.publishEvent(new ShipmentCompromisedEvent(
                        this,
                        shipment.getTrackingId(),
                        event.getTemperature(),
                        shipment.getMinTemperature(),
                        shipment.getMaxTemperature(),
                        shipment.getContactEmail()
                ));
            }
        });
    }
}