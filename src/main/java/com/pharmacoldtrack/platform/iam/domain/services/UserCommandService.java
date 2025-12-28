package com.pharmacoldtrack.platform.iam.domain.services;

import com.pharmacoldtrack.platform.iam.domain.model.aggregates.User;
import com.pharmacoldtrack.platform.iam.domain.model.commands.SignInCommand;
import com.pharmacoldtrack.platform.iam.domain.model.commands.SignUpCommand;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

public interface UserCommandService {

    /**
     * Registers a new user in the system.
     * @param command The registration data
     * @return The created user wrapped in an Optional
     */
    Optional<User> handle(SignUpCommand command);

    /**
     * Authenticates a user and generates a token.
     * @param command The sign-in credentials
     * @return A pair containing the User and the generated Token
     */
    Optional<ImmutablePair<User, String>> handle(SignInCommand command);
}