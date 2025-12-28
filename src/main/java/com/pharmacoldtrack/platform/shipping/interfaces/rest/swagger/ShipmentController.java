package com.pharmacoldtrack.platform.shipping.interfaces.rest.swagger;

import com.pharmacoldtrack.platform.shipping.interfaces.rest.dto.request.CreateShipmentResource;
import com.pharmacoldtrack.platform.shipping.interfaces.rest.dto.request.DeliveryShipmentResource;
import com.pharmacoldtrack.platform.shipping.interfaces.rest.dto.request.UpdateShipmentResource;
import com.pharmacoldtrack.platform.shipping.interfaces.rest.dto.response.ShipmentDetailResource;
import com.pharmacoldtrack.platform.shipping.interfaces.rest.dto.response.ShipmentResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "Get Shipment Details", description = "Retrieves shipment details including telemetry history.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Shipment found"),
            @ApiResponse(responseCode = "404", description = "Shipment not found")
    })
    @GetMapping("/{id}")
    ResponseEntity<ShipmentDetailResource> getShipmentById(@Parameter(description = "Shipment ID") @PathVariable Long id);

    @Operation(summary = "Update Shipment", description = "Updates shipment details only if status is CREATED.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Shipment updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid status or data"),
            @ApiResponse(responseCode = "404", description = "Shipment not found")
    })
    @PutMapping("/{id}")
    ResponseEntity<ShipmentResource> updateShipment(
            @Parameter(description = "Shipment ID") @PathVariable Long id,
            @RequestBody UpdateShipmentResource resource);

    @Operation(summary = "Register Shipment Departure", description = "Changes status from CREATED to IN_TRANSIT.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Departure registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid status transition"),
            @ApiResponse(responseCode = "404", description = "Shipment not found")
    })
    @PostMapping("/{id}/departure")
    ResponseEntity<ShipmentResource> departure(@Parameter(description = "Shipment ID") @PathVariable Long id);

    @Operation(summary = "Register Shipment Delivery", description = "Changes status from IN_TRANSIT to DELIVERED and records signature.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delivery registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid status transition"),
            @ApiResponse(responseCode = "404", description = "Shipment not found")
    })
    @PostMapping("/{id}/delivery")
    ResponseEntity<ShipmentResource> delivery(
            @Parameter(description = "Shipment ID") @PathVariable Long id,
            @RequestBody DeliveryShipmentResource resource
    );

    @Operation(summary = "Cancel Shipment", description = "Cancels a shipment if it is still in CREATED status.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Shipment cancelled successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid status for cancellation"),
            @ApiResponse(responseCode = "404", description = "Shipment not found")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> cancelShipment(@Parameter(description = "Shipment ID") @PathVariable Long id);
}