package com.pharmacoldtrack.platform.shipping.domain.model.valueobjects;

import com.pharmacoldtrack.platform.containermonitoring.domain.model.aggregates.TelemetryData;
import com.pharmacoldtrack.platform.shipping.domain.model.aggregates.Shipment;

import java.util.List;

public record ShipmentDetail(
        Shipment shipment,
        List<TelemetryData> telemetry
) {
}