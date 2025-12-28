package com.pharmacoldtrack.platform.iam.application.internal.commandservices;

import com.pharmacoldtrack.platform.iam.application.internal.outboundservices.hashing.HashingService;
import com.pharmacoldtrack.platform.iam.application.internal.outboundservices.tokens.TokenService;
import com.pharmacoldtrack.platform.iam.domain.model.aggregates.User;
import com.pharmacoldtrack.platform.iam.domain.model.commands.SignInCommand;
import com.pharmacoldtrack.platform.iam.domain.model.commands.SignUpCommand;
import com.pharmacoldtrack.platform.iam.domain.model.valueobjects.Roles;
import com.pharmacoldtrack.platform.iam.domain.services.UserCommandService;
import com.pharmacoldtrack.platform.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.pharmacoldtrack.platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;

    @Override
    public Optional<User> handle(SignUpCommand command) {
        if (userRepository.existsByUsername(command.username()))
            throw new RuntimeException("Username already exists");
        if (userRepository.existsByEmail(command.email()))
            throw new RuntimeException("Email already exists");

        // Creamos una lista mutable por si command.roles() es inmutable
        var roles = new ArrayList<>(command.roles());

        if (roles.isEmpty()) {
            var role = roleRepository.findByName(Roles.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Role not found"));
            roles.add(role);
        }

        // CORRECCIÓN: Llamada con el orden exacto (Username, Email, Password, Roles)
        var user = new User(
                command.username(),
                command.email(),
                hashingService.encode(command.password()),
                roles
        );

        userRepository.save(user);
        return Optional.of(user);
    }

    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByUsername(command.username())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!hashingService.matches(command.password(), user.getPassword()))
            throw new RuntimeException("Invalid password");

        var authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), null);
        var token = tokenService.generateToken(authentication);

        return Optional.of(ImmutablePair.of(user, token));
    }
}