package com.pharmacoldtrack.platform.shipping.domain.model.queries;

public record GetShipmentByIdQuery(Long id) {
    public GetShipmentByIdQuery {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("id must be greater than 0");
        }
    }
}