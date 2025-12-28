package com.pharmacoldtrack.platform.containermonitoring.domain.model.commands;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateTelemetryCommand(
        UUID shipmentTrackingId,
        Double latitude,
        Double longitude,
        Double temperature,
        LocalDateTime measuredAt
) {
}