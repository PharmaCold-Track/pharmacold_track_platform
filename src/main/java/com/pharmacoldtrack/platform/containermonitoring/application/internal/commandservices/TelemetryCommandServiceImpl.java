package com.pharmacoldtrack.platform.containermonitoring.application.internal.commandservices;

import com.pharmacoldtrack.platform.containermonitoring.domain.model.aggregates.TelemetryData;
import com.pharmacoldtrack.platform.containermonitoring.domain.model.commands.CreateTelemetryCommand;
import com.pharmacoldtrack.platform.containermonitoring.domain.model.events.TelemetryCreatedEvent;
import com.pharmacoldtrack.platform.containermonitoring.domain.persistence.TelemetryRepository;
import com.pharmacoldtrack.platform.containermonitoring.domain.services.TelemetryCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TelemetryCommandServiceImpl implements TelemetryCommandService {

    private final TelemetryRepository telemetryRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public Long handle(CreateTelemetryCommand command) {
        var telemetry = new TelemetryData(
                command.shipmentTrackingId(),
                command.latitude(),
                command.longitude(),
                command.temperature(),
                command.measuredAt()
        );
        telemetryRepository.save(telemetry);

        eventPublisher.publishEvent(new TelemetryCreatedEvent(
                this,
                telemetry.getShipmentTrackingId(),
                telemetry.getTemperature()
        ));

        return telemetry.getId();
    }
}