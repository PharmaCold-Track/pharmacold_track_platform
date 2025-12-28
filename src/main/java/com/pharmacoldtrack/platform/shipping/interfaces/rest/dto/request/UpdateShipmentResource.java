package com.pharmacoldtrack.platform.shipping.interfaces.rest.dto.request;

public record UpdateShipmentResource(
        String description,
        String origin,
        String destination,
        Double minTemperature,
        Double maxTemperature
) {
}