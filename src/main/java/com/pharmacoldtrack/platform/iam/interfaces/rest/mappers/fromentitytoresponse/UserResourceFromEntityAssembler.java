package com.pharmacoldtrack.platform.iam.interfaces.rest.mappers.fromentitytoresponse;

import com.pharmacoldtrack.platform.iam.domain.model.aggregates.User;
import com.pharmacoldtrack.platform.iam.domain.model.entities.Role;
import com.pharmacoldtrack.platform.iam.interfaces.rest.dto.response.UserResource;

import java.util.List;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User entity) {
        List<String> roles = entity.getRoles().stream()
                .map(Role::getStringName)
                .toList();

        return new UserResource(
                entity.getId(),
                entity.getUsername(),
                entity.getEmail(),
                roles
        );
    }
}