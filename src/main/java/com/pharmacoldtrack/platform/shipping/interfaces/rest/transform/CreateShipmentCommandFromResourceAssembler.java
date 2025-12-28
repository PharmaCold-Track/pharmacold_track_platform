package com.pharmacoldtrack.platform.shipping.interfaces.rest.transform;

import com.pharmacoldtrack.platform.shipping.domain.model.commands.CreateShipmentCommand;
import com.pharmacoldtrack.platform.shipping.interfaces.rest.dto.request.CreateShipmentResource;

public class CreateShipmentCommandFromResourceAssembler {
    public static CreateShipmentCommand toCommandFromResource(CreateShipmentResource resource) {
        return new CreateShipmentCommand(
                resource.description(),
                resource.origin(),
                resource.destination(),
                resource.minTemperature(),
                resource.maxTemperature(),
                resource.contactEmail(),
                resource.estimatedArrival()
        );
    }
}