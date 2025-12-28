package com.pharmacoldtrack.platform.iam.interfaces.rest.mappers.fromentitytoresponse;

import com.pharmacoldtrack.platform.iam.domain.model.aggregates.User;
import com.pharmacoldtrack.platform.iam.interfaces.rest.dto.response.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User entity, String token) {
        return new AuthenticatedUserResource(
                entity.getId(),
                entity.getUsername(),
                token
        );
    }
}