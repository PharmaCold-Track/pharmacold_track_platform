package com.pharmacoldtrack.platform.shipping.domain.model.commands;

public record UpdateShipmentCommand(
        Long id,
        String description,
        Double minTemperature,
        Double maxTemperature
) {
}