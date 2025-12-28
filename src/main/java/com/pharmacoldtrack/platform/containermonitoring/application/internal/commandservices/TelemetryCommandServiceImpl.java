package com.pharmacoldtrack.platform.containermonitoring.application.internal.commandservices;

import com.pharmacoldtrack.platform.containermonitoring.domain.model.aggregates.TelemetryData;
import com.pharmacoldtrack.platform.containermonitoring.domain.model.commands.CreateTelemetryCommand;
import com.pharmacoldtrack.platform.containermonitoring.domain.persistence.TelemetryRepository;
import com.pharmacoldtrack.platform.containermonitoring.domain.services.TelemetryCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TelemetryCommandServiceImpl implements TelemetryCommandService {

    private final TelemetryRepository telemetryRepository;

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
        return telemetry.getId();
    }
}