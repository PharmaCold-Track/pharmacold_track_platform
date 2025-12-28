package com.pharmacoldtrack.platform.containermonitoring.interfaces.rest.transform;

import com.pharmacoldtrack.platform.containermonitoring.domain.model.commands.CreateTelemetryCommand;
import com.pharmacoldtrack.platform.containermonitoring.interfaces.rest.dto.request.CreateTelemetryResource;

public class CreateTelemetryCommandFromResourceAssembler {
    public static CreateTelemetryCommand toCommandFromResource(CreateTelemetryResource resource) {
        return new CreateTelemetryCommand(
                resource.shipmentTrackingId(),
                resource.latitude(),
                resource.longitude(),
                resource.temperature(),
                resource.timestamp()
        );
    }
}