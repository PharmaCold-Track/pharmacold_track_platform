package com.pharmacoldtrack.platform.iam.interfaces.rest.controllers;

import com.pharmacoldtrack.platform.iam.domain.services.UserCommandService;
import com.pharmacoldtrack.platform.iam.interfaces.rest.dto.request.SignInResource;
import com.pharmacoldtrack.platform.iam.interfaces.rest.dto.request.SignUpResource;
import com.pharmacoldtrack.platform.iam.interfaces.rest.dto.response.AuthenticatedUserResource;
import com.pharmacoldtrack.platform.iam.interfaces.rest.dto.response.UserResource;
import com.pharmacoldtrack.platform.iam.interfaces.rest.mappers.fromentitytoresponse.AuthenticatedUserResourceFromEntityAssembler;
import com.pharmacoldtrack.platform.iam.interfaces.rest.mappers.fromentitytoresponse.UserResourceFromEntityAssembler;
import com.pharmacoldtrack.platform.iam.interfaces.rest.mappers.fromresourcetocommand.SignInCommandFromResourceAssembler;
import com.pharmacoldtrack.platform.iam.interfaces.rest.mappers.fromresourcetocommand.SignUpCommandFromResourceAssembler;
import com.pharmacoldtrack.platform.iam.interfaces.rest.swagger.AuthenticationController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationControllerImpl implements AuthenticationController {

    private final UserCommandService userCommandService;

    @Override
    public ResponseEntity<UserResource> signUp(SignUpResource resource) {
        var command = SignUpCommandFromResourceAssembler.toCommandFromResource(resource);
        var user = userCommandService.handle(command);

        if (user.isEmpty()) return ResponseEntity.badRequest().build();

        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<AuthenticatedUserResource> signIn(SignInResource resource) {
        var command = SignInCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = userCommandService.handle(command);

        if (result.isEmpty()) return ResponseEntity.badRequest().build();

        var authenticatedUser = result.get();
        var response = AuthenticatedUserResourceFromEntityAssembler.toResourceFromEntity(
                authenticatedUser.getLeft(),
                authenticatedUser.getRight()
        );
        return ResponseEntity.ok(response);
    }
}