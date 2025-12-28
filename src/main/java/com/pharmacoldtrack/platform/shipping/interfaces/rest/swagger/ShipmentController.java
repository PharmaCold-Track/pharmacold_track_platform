package com.pharmacoldtrack.platform.shipping.interfaces.rest.swagger;

import com.pharmacoldtrack.platform.shipping.interfaces.rest.dto.request.CreateShipmentResource;
import com.pharmacoldtrack.platform.shipping.interfaces.rest.dto.response.ShipmentResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Shipping", description = "Shipment Management Endpoints")
@RequestMapping(value = "/shipments", produces = MediaType.APPLICATION_JSON_VALUE)
public interface ShipmentController {

    @Operation(summary = "Create a new Shipment", description = "Registers a new shipment with origin, destination and temperature limits.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Shipment created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    ResponseEntity<ShipmentResource> createShipment(@RequestBody CreateShipmentResource resource);
}