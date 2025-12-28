package com.pharmacoldtrack.platform.shipping.interfaces.rest.dto.request;

public record DeliveryShipmentResource(
        String recipientSignature,
        String notes
) {
}