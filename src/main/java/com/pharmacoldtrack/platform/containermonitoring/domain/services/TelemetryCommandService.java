package com.pharmacoldtrack.platform.containermonitoring.domain.services;

import com.pharmacoldtrack.platform.containermonitoring.domain.model.commands.CreateTelemetryCommand;

public interface TelemetryCommandService {
    Long handle(CreateTelemetryCommand command);
}