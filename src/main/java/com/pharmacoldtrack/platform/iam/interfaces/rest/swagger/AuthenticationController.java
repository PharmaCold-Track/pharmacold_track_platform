package com.pharmacoldtrack.platform.iam.interfaces.rest.swagger;

import com.pharmacoldtrack.platform.iam.interfaces.rest.dto.request.SignInResource;
import com.pharmacoldtrack.platform.iam.interfaces.rest.dto.request.SignUpResource;
import com.pharmacoldtrack.platform.iam.interfaces.rest.dto.response.AuthenticatedUserResource;
import com.pharmacoldtrack.platform.iam.interfaces.rest.dto.response.UserResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/authentication", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Authentication", description = "Authentication Endpoints")
public interface AuthenticationController {

    @PostMapping("/sign-up")
    @Operation(summary = "Sign up a new user", description = "Registers a new user in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    ResponseEntity<UserResource> signUp(@RequestBody SignUpResource resource);

    @PostMapping("/sign-in")
    @Operation(summary = "Sign in a user", description = "Authenticates a user and returns a token.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User signed in successfully."),
            @ApiResponse(responseCode = "400", description = "Invalid credentials.")
    })
    ResponseEntity<AuthenticatedUserResource> signIn(@RequestBody SignInResource resource);
}