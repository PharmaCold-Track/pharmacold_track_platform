package com.pharmacoldtrack.platform.containermonitoring.domain.model.aggregates;

import com.pharmacoldtrack.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "telemetry_data")
@Getter
@NoArgsConstructor
public class TelemetryData extends AuditableAbstractAggregateRoot<TelemetryData> {

    @Column(nullable = false)
    private UUID shipmentTrackingId;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    private Double temperature;

    @Column(nullable = false)
    private LocalDateTime measuredAt;

    public TelemetryData(UUID shipmentTrackingId, Double latitude, Double longitude, Double temperature, LocalDateTime measuredAt) {
        this.shipmentTrackingId = shipmentTrackingId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.temperature = temperature;
        this.measuredAt = measuredAt;
    }
}