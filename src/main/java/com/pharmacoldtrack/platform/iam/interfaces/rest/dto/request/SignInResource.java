package com.pharmacoldtrack.platform.iam.interfaces.rest.dto.request;

public record SignInResource(
        String username,
        String password
) {
}