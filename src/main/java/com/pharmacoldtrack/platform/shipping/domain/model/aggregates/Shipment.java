package com.pharmacoldtrack.platform.shipping.domain.model.aggregates;

import com.pharmacoldtrack.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.pharmacoldtrack.platform.shipping.domain.model.valueobjects.ShipmentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "shipments")
@NoArgsConstructor
public class Shipment extends AuditableAbstractAggregateRoot<Shipment> {

    @Column(nullable = false, unique = true)
    private UUID trackingId;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ShipmentStatus status;

    @Column(nullable = false)
    private Double minTemperature;

    @Column(nullable = false)
    private Double maxTemperature;

    @Column(nullable = false)
    private String origin;

    @Column(nullable = false)
    private String destination;

    @Column(nullable = false)
    private LocalDateTime estimatedArrival;

    @Column(nullable = false)
    private String contactEmail;

    public Shipment(String description, String origin, String destination,
                    Double minTemperature, Double maxTemperature,
                    String contactEmail, LocalDateTime estimatedArrival) {

        if (minTemperature >= maxTemperature) {
            throw new IllegalArgumentException("Minimum temperature must be less than maximum temperature");
        }

        this.trackingId = UUID.randomUUID();
        this.status = ShipmentStatus.CREATED;
        this.description = description;
        this.origin = origin;
        this.destination = destination;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.contactEmail = contactEmail;
        this.estimatedArrival = estimatedArrival;
    }

    public void update(String description, String origin, String destination, Double minTemperature, Double maxTemperature) {
        if (this.status != ShipmentStatus.CREATED) {
            throw new IllegalStateException("Shipment can only be updated when status is CREATED");
        }

        if (minTemperature >= maxTemperature) {
            throw new IllegalArgumentException("Minimum temperature must be less than maximum temperature");
        }

        this.description = description;
        this.origin = origin;
        this.destination = destination;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
    }
}