package com.pharmacoldtrack.platform.shipping.application.internal.commandservices;

import com.pharmacoldtrack.platform.shipping.domain.model.aggregates.Shipment;
import com.pharmacoldtrack.platform.shipping.domain.model.commands.CreateShipmentCommand;
import com.pharmacoldtrack.platform.shipping.domain.model.commands.UpdateShipmentCommand;
import com.pharmacoldtrack.platform.shipping.infrastructure.persistence.jpa.repositories.ShipmentRepository;
import com.pharmacoldtrack.platform.shipping.domain.services.ShipmentCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShipmentCommandServiceImpl implements ShipmentCommandService {

    private final ShipmentRepository shipmentRepository;

    @Override
    public Optional<Shipment> handle(CreateShipmentCommand command) {
        var shipment = new Shipment(
                command.description(),
                command.origin(),
                command.destination(),
                command.minTemperature(),
                command.maxTemperature(),
                command.contactEmail(),
                command.estimatedArrival()
        );
        shipmentRepository.save(shipment);
        return Optional.of(shipment);
    }

    @Override
    public Optional<Shipment> handle(UpdateShipmentCommand command) {
        var shipmentOptional = shipmentRepository.findById(command.id());

        if (shipmentOptional.isEmpty()) return Optional.empty();

        var shipment = shipmentOptional.get();
        try {
            shipment.update(
                    command.description(),
                    command.minTemperature(),
                    command.maxTemperature()
            );
            shipmentRepository.save(shipment);
            return Optional.of(shipment);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}