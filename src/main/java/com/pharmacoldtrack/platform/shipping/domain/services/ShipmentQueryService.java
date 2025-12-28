package com.pharmacoldtrack.platform.shipping.domain.services;

import com.pharmacoldtrack.platform.shipping.domain.model.aggregates.Shipment;
import com.pharmacoldtrack.platform.shipping.domain.model.queries.GetAllShipmentsByFilterQuery;
import com.pharmacoldtrack.platform.shipping.domain.model.queries.GetShipmentByIdQuery;
import com.pharmacoldtrack.platform.shipping.domain.model.valueobjects.ShipmentDetail;

import java.util.List;
import java.util.Optional;

public interface ShipmentQueryService {
    Optional<ShipmentDetail> handle(GetShipmentByIdQuery query);
    List<Shipment> handle(GetAllShipmentsByFilterQuery query);
}