package com.pharmacoldtrack.platform.iam.interfaces.rest.dto.response;

public record AuthenticatedUserResource(
        Long id,
        String username,
        String token
) {
}