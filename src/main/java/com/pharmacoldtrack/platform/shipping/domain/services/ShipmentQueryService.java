package com.pharmacoldtrack.platform.shipping.domain.services;

import com.pharmacoldtrack.platform.shipping.domain.model.aggregates.Shipment;
import com.pharmacoldtrack.platform.shipping.domain.model.queries.GetShipmentByIdQuery;

import java.util.Optional;

public interface ShipmentQueryService {
    Optional<Shipment> handle(GetShipmentByIdQuery query);
}