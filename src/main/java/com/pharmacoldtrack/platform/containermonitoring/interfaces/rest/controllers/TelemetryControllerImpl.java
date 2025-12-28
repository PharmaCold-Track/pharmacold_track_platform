package com.pharmacoldtrack.platform.containermonitoring.interfaces.rest.controllers;

import com.pharmacoldtrack.platform.containermonitoring.domain.services.TelemetryCommandService;
import com.pharmacoldtrack.platform.containermonitoring.interfaces.rest.dto.request.CreateTelemetryResource;
import com.pharmacoldtrack.platform.containermonitoring.interfaces.rest.dto.response.TelemetryIdResource;
import com.pharmacoldtrack.platform.containermonitoring.interfaces.rest.swagger.TelemetryController;
import com.pharmacoldtrack.platform.containermonitoring.interfaces.rest.transform.CreateTelemetryCommandFromResourceAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TelemetryControllerImpl implements TelemetryController {

    private final TelemetryCommandService telemetryCommandService;

    @Override
    public ResponseEntity<TelemetryIdResource> createTelemetry(CreateTelemetryResource resource) {
        var command = CreateTelemetryCommandFromResourceAssembler.toCommandFromResource(resource);
        var telemetryId = telemetryCommandService.handle(command);
        return new ResponseEntity<>(new TelemetryIdResource(telemetryId), HttpStatus.CREATED);
    }
}