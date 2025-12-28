package com.pharmacoldtrack.platform.shipping.interfaces.rest.controllers;

import com.pharmacoldtrack.platform.shipping.domain.model.queries.GetShipmentByIdQuery;
import com.pharmacoldtrack.platform.shipping.domain.services.ShipmentCommandService;
import com.pharmacoldtrack.platform.shipping.domain.services.ShipmentQueryService;
import com.pharmacoldtrack.platform.shipping.interfaces.rest.dto.request.CreateShipmentResource;
import com.pharmacoldtrack.platform.shipping.interfaces.rest.dto.response.ShipmentDetailResource;
import com.pharmacoldtrack.platform.shipping.interfaces.rest.dto.response.ShipmentResource;
import com.pharmacoldtrack.platform.shipping.interfaces.rest.swagger.ShipmentController;
import com.pharmacoldtrack.platform.shipping.interfaces.rest.transform.CreateShipmentCommandFromResourceAssembler;
import com.pharmacoldtrack.platform.shipping.interfaces.rest.transform.ShipmentDetailResourceFromEntityAssembler;
import com.pharmacoldtrack.platform.shipping.interfaces.rest.transform.ShipmentResourceFromEntityAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ShipmentControllerImpl implements ShipmentController {

    private final ShipmentCommandService shipmentCommandService;
    private final ShipmentQueryService shipmentQueryService;

    @Override
    public ResponseEntity<ShipmentResource> createShipment(CreateShipmentResource resource) {
        var command = CreateShipmentCommandFromResourceAssembler.toCommandFromResource(resource);
        var shipment = shipmentCommandService.handle(command);

        if (shipment.isEmpty()) return ResponseEntity.badRequest().build();

        var shipmentResource = ShipmentResourceFromEntityAssembler.toResourceFromEntity(shipment.get());
        return new ResponseEntity<>(shipmentResource, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ShipmentDetailResource> getShipmentById(Long id) {
        var query = new GetShipmentByIdQuery(id);
        var shipment = shipmentQueryService.handle(query);

        if (shipment.isEmpty()) return ResponseEntity.notFound().build();

        var resource = ShipmentDetailResourceFromEntityAssembler.toResourceFromEntity(shipment.get());
        return ResponseEntity.ok(resource);
    }
}