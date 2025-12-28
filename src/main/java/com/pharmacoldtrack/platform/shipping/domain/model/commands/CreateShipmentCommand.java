package com.pharmacoldtrack.platform.shipping.domain.model.commands;

import java.time.LocalDateTime;

public record CreateShipmentCommand(
        String description,
        String origin,
        String destination,
        Double minTemperature,
        Double maxTemperature,
        String contactEmail,
        LocalDateTime estimatedArrival
) {
}