package com.pharmacoldtrack.platform.shipping.infrastructure.persistence.jpa.repositories;

import com.pharmacoldtrack.platform.shipping.domain.model.aggregates.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long>, JpaSpecificationExecutor<Shipment> {
    Optional<Shipment> findByTrackingId(UUID trackingId);
}