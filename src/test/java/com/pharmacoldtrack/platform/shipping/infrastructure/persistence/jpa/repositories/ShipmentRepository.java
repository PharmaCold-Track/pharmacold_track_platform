package com.pharmacoldtrack.platform.shipping.domain.persistence;

import com.pharmacoldtrack.platform.shipping.domain.model.aggregates.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    Optional<Shipment> findByTrackingId(UUID trackingId);
}