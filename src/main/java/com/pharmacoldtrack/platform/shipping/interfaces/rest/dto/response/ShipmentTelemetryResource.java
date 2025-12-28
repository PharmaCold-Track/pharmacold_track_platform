package com.pharmacoldtrack.platform.shipping.interfaces.rest.dto.response;

import java.time.LocalDateTime;

public record ShipmentTelemetryResource(
        Double latitude,
        Double longitude,
        Double temperature,
        LocalDateTime timestamp
) {
}