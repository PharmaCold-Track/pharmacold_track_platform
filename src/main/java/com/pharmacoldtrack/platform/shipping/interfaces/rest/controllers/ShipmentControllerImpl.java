package com.pharmacoldtrack.platform.shipping.interfaces.rest.controllers;

import com.pharmacoldtrack.platform.shipping.domain.services.ShipmentCommandService;
import com.pharmacoldtrack.platform.shipping.interfaces.rest.dto.request.CreateShipmentResource;
import com.pharmacoldtrack.platform.shipping.interfaces.rest.dto.response.ShipmentResource;
import com.pharmacoldtrack.platform.shipping.interfaces.rest.swagger.ShipmentController;
import com.pharmacoldtrack.platform.shipping.interfaces.rest.transform.CreateShipmentCommandFromResourceAssembler;
import com.pharmacoldtrack.platform.shipping.interfaces.rest.transform.ShipmentResourceFromEntityAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ShipmentControllerImpl implements ShipmentController {

    private final ShipmentCommandService shipmentCommandService;

    @Override
    public ResponseEntity<ShipmentResource> createShipment(CreateShipmentResource resource) {
        var command = CreateShipmentCommandFromResourceAssembler.toCommandFromResource(resource);
        var shipment = shipmentCommandService.handle(command);

        if (shipment.isEmpty()) return ResponseEntity.badRequest().build();

        var shipmentResource = ShipmentResourceFromEntityAssembler.toResourceFromEntity(shipment.get());
        return new ResponseEntity<>(shipmentResource, HttpStatus.CREATED);
    }
}