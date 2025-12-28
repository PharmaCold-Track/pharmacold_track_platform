package com.pharmacoldtrack.platform.shipping.domain.services;

import com.pharmacoldtrack.platform.shipping.domain.model.queries.GetShipmentByIdQuery;
import com.pharmacoldtrack.platform.shipping.domain.model.valueobjects.ShipmentDetail;

import java.util.Optional;

public interface ShipmentQueryService {
    Optional<ShipmentDetail> handle(GetShipmentByIdQuery query);
}