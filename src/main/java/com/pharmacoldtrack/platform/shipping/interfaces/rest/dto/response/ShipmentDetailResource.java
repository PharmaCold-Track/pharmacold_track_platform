package com.pharmacoldtrack.platform.shipping.interfaces.rest.dto.response;

import java.util.List;

public record ShipmentDetailResource(
        Long id,
        String status,
        List<ShipmentTelemetryResource> telemetry,
        Double currentTemp
) {
}