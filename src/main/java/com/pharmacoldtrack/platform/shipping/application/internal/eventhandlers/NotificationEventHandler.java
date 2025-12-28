package com.pharmacoldtrack.platform.shipping.application.internal.eventhandlers;

import com.pharmacoldtrack.platform.shared.application.external.ExternalNotificationService;
import com.pharmacoldtrack.platform.shipping.domain.model.events.ShipmentCompromisedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationEventHandler {

    private final ExternalNotificationService notificationService;

    @Async
    @EventListener
    public void on(ShipmentCompromisedEvent event) {
        String subject = "ALERT: Shipment " + event.getTrackingId() + " Compromised!";
        String message = String.format(
                "Warning! Your shipment has exceeded temperature limits.\n" +
                        "Current Temp: %.2f\n" +
                        "Allowed Range: [%.2f - %.2f]\n" +
                        "Please take immediate action.",
                event.getCurrentTemperature(),
                event.getMinLimit(),
                event.getMaxLimit()
        );

        notificationService.sendAlert(event.getContactEmail(), subject, message);
    }
}