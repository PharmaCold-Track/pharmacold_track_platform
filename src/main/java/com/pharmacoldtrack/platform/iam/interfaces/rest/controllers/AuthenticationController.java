package com.pharmacoldtrack.platform.iam.interfaces.rest.controllers;

import com.pharmacoldtrack.platform.iam.domain.model.commands.SignInCommand;
import com.pharmacoldtrack.platform.iam.domain.model.commands.SignUpCommand;
import com.pharmacoldtrack.platform.iam.domain.services.UserCommandService;
import com.pharmacoldtrack.platform.iam.interfaces.rest.dto.request.SignInResource;
import com.pharmacoldtrack.platform.iam.interfaces.rest.dto.request.SignUpResource;
import com.pharmacoldtrack.platform.iam.interfaces.rest.dto.response.AuthenticatedUserResource;
import com.pharmacoldtrack.platform.iam.interfaces.rest.dto.response.UserResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/authentication")
@Tag(name = "Authentication", description = "Authentication Endpoints")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserCommandService userCommandService;

    @PostMapping("/sign-up")
    public ResponseEntity<UserResource> signUp(@RequestBody SignUpResource resource) {
        var command = new SignUpCommand(
                resource.username(),
                resource.email(),
                resource.password(),
                Collections.emptyList()
        );

        var user = userCommandService.handle(command);

        if (user.isEmpty()) return ResponseEntity.badRequest().build();

        var userEntity = user.get();
        var userResource = new UserResource(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getEmail(),
                List.of("ROLE_USER")
        );
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<AuthenticatedUserResource> signIn(@RequestBody SignInResource resource) {
        var command = new SignInCommand(resource.username(), resource.password());
        var result = userCommandService.handle(command);

        if (result.isEmpty()) return ResponseEntity.badRequest().build();

        var pair = result.get();
        var response = new AuthenticatedUserResource(
                pair.getLeft().getId(),
                pair.getLeft().getUsername(),
                pair.getRight()
        );
        return ResponseEntity.ok(response);
    }
}