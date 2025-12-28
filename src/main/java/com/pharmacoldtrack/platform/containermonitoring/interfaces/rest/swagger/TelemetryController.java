package com.pharmacoldtrack.platform.containermonitoring.interfaces.rest.swagger;

import com.pharmacoldtrack.platform.containermonitoring.interfaces.rest.dto.request.CreateTelemetryResource;
import com.pharmacoldtrack.platform.containermonitoring.interfaces.rest.dto.response.TelemetryIdResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Telemetry", description = "Container Monitoring & Telemetry Ingestion")
@RequestMapping(value = "/telemetry", produces = MediaType.APPLICATION_JSON_VALUE)
public interface TelemetryController {

    @Operation(summary = "Ingest Telemetry Data", description = "Receives sensor data (IoT) for a specific shipment.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Telemetry recorded successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid data")
    })
    @PostMapping
    ResponseEntity<TelemetryIdResource> createTelemetry(@RequestBody CreateTelemetryResource resource);
}