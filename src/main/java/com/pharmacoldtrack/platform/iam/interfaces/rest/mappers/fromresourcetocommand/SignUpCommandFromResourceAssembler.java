package com.pharmacoldtrack.platform.iam.interfaces.rest.mappers.fromresourcetocommand;

import com.pharmacoldtrack.platform.iam.domain.model.commands.SignUpCommand;
import com.pharmacoldtrack.platform.iam.interfaces.rest.dto.request.SignUpResource;

import java.util.Collections;

public class SignUpCommandFromResourceAssembler {
    public static SignUpCommand toCommandFromResource(SignUpResource resource) {
        return new SignUpCommand(
                resource.username(),
                resource.email(),
                resource.password(),
                Collections.emptyList() // El servicio se encarga de asignar el rol por defecto
        );
    }
}