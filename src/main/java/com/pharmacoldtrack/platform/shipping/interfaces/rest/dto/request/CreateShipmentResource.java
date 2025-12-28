package com.pharmacoldtrack.platform.shipping.interfaces.rest.dto.request;

import java.time.LocalDateTime;

public record CreateShipmentResource(
        String description,
        String origin,
        String destination,
        Double minTemperature,
        Double maxTemperature,
        String contactEmail,
        LocalDateTime estimatedArrival
) {
}