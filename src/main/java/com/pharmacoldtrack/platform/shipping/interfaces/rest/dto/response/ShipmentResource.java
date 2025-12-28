package com.pharmacoldtrack.platform.shipping.interfaces.rest.dto.response;

import java.util.UUID;

public record ShipmentResource(
        Long id,
        UUID trackingId,
        String status,
        String description,
        String origin,
        String destination,
        Double minTemperature,
        Double maxTemperature,
        String estimatedArrival
) {
}