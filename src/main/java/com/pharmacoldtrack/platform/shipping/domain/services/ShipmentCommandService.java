package com.pharmacoldtrack.platform.shipping.domain.services;

import com.pharmacoldtrack.platform.shipping.domain.model.aggregates.Shipment;
import com.pharmacoldtrack.platform.shipping.domain.model.commands.CreateShipmentCommand;
import com.pharmacoldtrack.platform.shipping.domain.model.commands.UpdateShipmentCommand;

import java.util.Optional;

public interface ShipmentCommandService {
    Optional<Shipment> handle(CreateShipmentCommand command);
    Optional<Shipment> handle(UpdateShipmentCommand command);
}