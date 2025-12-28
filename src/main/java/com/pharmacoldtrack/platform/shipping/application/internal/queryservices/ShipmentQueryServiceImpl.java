package com.pharmacoldtrack.platform.shipping.application.internal.queryservices;

import com.pharmacoldtrack.platform.containermonitoring.domain.persistence.TelemetryRepository;
import com.pharmacoldtrack.platform.shipping.domain.model.valueobjects.ShipmentDetail;
import com.pharmacoldtrack.platform.shipping.domain.model.queries.GetShipmentByIdQuery;
import com.pharmacoldtrack.platform.shipping.domain.services.ShipmentQueryService;
import com.pharmacoldtrack.platform.shipping.infrastructure.persistence.jpa.repositories.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShipmentQueryServiceImpl implements ShipmentQueryService {

    private final ShipmentRepository shipmentRepository;
    private final TelemetryRepository telemetryRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<ShipmentDetail> handle(GetShipmentByIdQuery query) {
        var shipmentOptional = shipmentRepository.findById(query.id());
        if (shipmentOptional.isEmpty()) return Optional.empty();

        var shipment = shipmentOptional.get();

        var telemetryData = telemetryRepository.findByShipmentTrackingId(shipment.getTrackingId());

        return Optional.of(new ShipmentDetail(shipment, telemetryData));
    }
}