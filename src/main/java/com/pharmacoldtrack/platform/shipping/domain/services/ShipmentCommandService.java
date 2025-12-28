package com.pharmacoldtrack.platform.shipping.domain.services;

import com.pharmacoldtrack.platform.shipping.domain.model.aggregates.Shipment;
import com.pharmacoldtrack.platform.shipping.domain.model.commands.*;

import java.util.Optional;

public interface ShipmentCommandService {
    Optional<Shipment> handle(CreateShipmentCommand command);
    Optional<Shipment> handle(UpdateShipmentCommand command);
    Optional<Shipment> handle(DepartureShipmentCommand command);
    Optional<Shipment> handle(DeliveryShipmentCommand command);
    Optional<Shipment> handle(CancelShipmentCommand command);
}