package com.pharmacoldtrack.platform.shipping.interfaces.rest.transform;

import com.pharmacoldtrack.platform.shipping.domain.model.aggregates.Shipment;
import com.pharmacoldtrack.platform.shipping.interfaces.rest.dto.response.ShipmentDetailResource;

import java.util.Collections;

public class ShipmentDetailResourceFromEntityAssembler {
    public static ShipmentDetailResource toResourceFromEntity(Shipment entity) {
        // TODO: Integrate with Telemetry module to get real history and current temp
        return new ShipmentDetailResource(
                entity.getId(),
                entity.getStatus().name(),
                Collections.emptyList(), // Placeholder: No telemetry yet
                0.0                      // Placeholder: No sensors yet
        );
    }
}