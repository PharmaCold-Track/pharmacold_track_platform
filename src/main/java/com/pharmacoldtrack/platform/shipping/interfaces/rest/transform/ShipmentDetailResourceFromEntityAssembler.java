package com.pharmacoldtrack.platform.shipping.interfaces.rest.transform;

import com.pharmacoldtrack.platform.containermonitoring.domain.model.aggregates.TelemetryData;
import com.pharmacoldtrack.platform.shipping.domain.model.valueobjects.ShipmentDetail;
import com.pharmacoldtrack.platform.shipping.interfaces.rest.dto.response.ShipmentDetailResource;

import java.util.Comparator;
import java.util.List;

public class ShipmentDetailResourceFromEntityAssembler {

    public static ShipmentDetailResource toResourceFromEntity(ShipmentDetail detail) {
        var shipment = detail.shipment();
        var telemetryList = detail.telemetry();

        List<Double> temperatureHistory = telemetryList.stream()
                .sorted(Comparator.comparing(TelemetryData::getMeasuredAt))
                .map(TelemetryData::getTemperature)
                .toList();

        Double currentTemp = telemetryList.stream()
                .max(Comparator.comparing(TelemetryData::getMeasuredAt))
                .map(TelemetryData::getTemperature)
                .orElse(null);

        return new ShipmentDetailResource(
                shipment.getId(),
                shipment.getStatus().name(),
                temperatureHistory,
                currentTemp
        );
    }
}