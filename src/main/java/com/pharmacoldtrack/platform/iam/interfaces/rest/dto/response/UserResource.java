package com.pharmacoldtrack.platform.iam.interfaces.rest.dto.response;

import java.util.List;

public record UserResource(
        Long id,
        String username,
        String email,
        List<String> roles
) {
}