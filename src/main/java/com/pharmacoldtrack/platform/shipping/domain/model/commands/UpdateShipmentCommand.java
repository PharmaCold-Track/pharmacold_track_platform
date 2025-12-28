package com.pharmacoldtrack.platform.shipping.domain.model.commands;

public record UpdateShipmentCommand(
        Long id,
        String description,
        String origin,
        String destination,
        Double minTemperature,
        Double maxTemperature
) {
}