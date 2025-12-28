package com.pharmacoldtrack.platform.shipping.interfaces.rest.transform;

import com.pharmacoldtrack.platform.shipping.domain.model.commands.UpdateShipmentCommand;
import com.pharmacoldtrack.platform.shipping.interfaces.rest.dto.request.UpdateShipmentResource;

public class UpdateShipmentCommandFromResourceAssembler {
    public static UpdateShipmentCommand toCommandFromResource(Long id, UpdateShipmentResource resource) {
        return new UpdateShipmentCommand(
                id,
                resource.description(),
                resource.origin(),
                resource.destination(),
                resource.minTemperature(),
                resource.maxTemperature()
        );
    }
}