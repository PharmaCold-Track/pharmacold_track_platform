package com.pharmacoldtrack.platform.iam.interfaces.rest.dto.request;

public record SignUpResource(
        String username,
        String email,
        String password
) {
}