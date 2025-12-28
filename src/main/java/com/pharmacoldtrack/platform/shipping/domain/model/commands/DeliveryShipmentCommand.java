package com.pharmacoldtrack.platform.shipping.domain.model.commands;

public record DeliveryShipmentCommand(
        Long id,
        String recipientSignature,
        String notes
) {
}