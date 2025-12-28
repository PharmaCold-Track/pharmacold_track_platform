package com.pharmacoldtrack.platform.containermonitoring.domain.persistence;

import com.pharmacoldtrack.platform.containermonitoring.domain.model.aggregates.TelemetryData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;

@Repository
public interface TelemetryRepository extends JpaRepository<TelemetryData, Long> {
    List<TelemetryData> findByShipmentTrackingId(UUID shipmentTrackingId);
}