package com.pharmacoldtrack.platform.shipping.application.internal.queryservices;

import com.pharmacoldtrack.platform.containermonitoring.domain.persistence.TelemetryRepository;
import com.pharmacoldtrack.platform.shipping.domain.model.aggregates.Shipment;
import com.pharmacoldtrack.platform.shipping.domain.model.queries.GetAllShipmentsByFilterQuery;
import com.pharmacoldtrack.platform.shipping.domain.model.valueobjects.ShipmentDetail;
import com.pharmacoldtrack.platform.shipping.domain.model.queries.GetShipmentByIdQuery;
import com.pharmacoldtrack.platform.shipping.domain.services.ShipmentQueryService;
import com.pharmacoldtrack.platform.shipping.infrastructure.persistence.jpa.repositories.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    @Override
    @Transactional(readOnly = true)
    public List<Shipment> handle(GetAllShipmentsByFilterQuery query) {
        // Construcción dinámica de la consulta
        Specification<Shipment> spec = Specification.where(null);

        if (query.status().isPresent()) {
            spec = spec.and((root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("status"), query.status().get()));
        }

        if (query.fromDate().isPresent()) {
            spec = spec.and((root, criteriaQuery, criteriaBuilder) ->
                    // Usamos "createdAt" que viene de AuditableAbstractAggregateRoot
                    criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), query.fromDate().get()));
        }

        return shipmentRepository.findAll(spec);
    }
}