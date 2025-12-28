package com.pharmacoldtrack.platform.iam.interfaces.rest.mappers.fromresourcetocommand;

import com.pharmacoldtrack.platform.iam.domain.model.commands.SignInCommand;
import com.pharmacoldtrack.platform.iam.interfaces.rest.dto.request.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource resource) {
        return new SignInCommand(
                resource.username(),
                resource.password()
        );
    }
}