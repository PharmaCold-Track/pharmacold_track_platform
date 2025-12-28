package com.pharmacoldtrack.platform.shipping.domain.model.queries;

import com.pharmacoldtrack.platform.shipping.domain.model.valueobjects.ShipmentStatus;

import java.time.LocalDateTime;
import java.util.Optional;

public record GetAllShipmentsByFilterQuery(
        Optional<ShipmentStatus> status,
        Optional<LocalDateTime> fromDate
) {
}