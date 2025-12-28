package com.pharmacoldtrack.platform.shipping.interfaces.rest.transform;

import com.pharmacoldtrack.platform.shipping.domain.model.aggregates.Shipment;
import com.pharmacoldtrack.platform.shipping.interfaces.rest.dto.response.ShipmentResource;

public class ShipmentResourceFromEntityAssembler {
    public static ShipmentResource toResourceFromEntity(Shipment entity) {
        return new ShipmentResource(
                entity.getId(),
                entity.getTrackingId(),
                entity.getStatus().name(),
                entity.getDescription(),
                entity.getOrigin(),
                entity.getDestination(),
                entity.getMinTemperature(),
                entity.getMaxTemperature(),
                entity.getEstimatedArrival().toString()
        );
    }
}