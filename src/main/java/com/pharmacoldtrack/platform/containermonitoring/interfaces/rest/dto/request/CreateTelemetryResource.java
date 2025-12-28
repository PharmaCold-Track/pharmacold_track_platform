package com.pharmacoldtrack.platform.containermonitoring.interfaces.rest.dto.request;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateTelemetryResource(
        UUID shipmentTrackingId,
        Double latitude,
        Double longitude,
        Double temperature,
        LocalDateTime timestamp
) {
}