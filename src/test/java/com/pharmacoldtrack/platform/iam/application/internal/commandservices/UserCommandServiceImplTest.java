package com.pharmacoldtrack.platform.iam.application.internal.commandservices;

import com.pharmacoldtrack.platform.iam.application.internal.outboundservices.hashing.HashingService;
import com.pharmacoldtrack.platform.iam.application.internal.outboundservices.tokens.TokenService;
import com.pharmacoldtrack.platform.iam.domain.model.aggregates.User;
import com.pharmacoldtrack.platform.iam.domain.model.commands.SignInCommand;
import com.pharmacoldtrack.platform.iam.domain.model.commands.SignUpCommand;
import com.pharmacoldtrack.platform.iam.domain.model.entities.Role;
import com.pharmacoldtrack.platform.iam.domain.model.valueobjects.Roles;
import com.pharmacoldtrack.platform.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.pharmacoldtrack.platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserCommandServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private HashingService hashingService;

    @Mock
    private TokenService tokenService;

    @InjectMocks
    private UserCommandServiceImpl userCommandService;

    @Test
    void handleSignUp_WhenUsernameAlreadyExists_ShouldThrowException() {
        // Arrange
        SignUpCommand command = new SignUpCommand("existingUser", "test@email.com", "password", Collections.emptyList());
        when(userRepository.existsByUsername(command.username())).thenReturn(true);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> userCommandService.handle(command));
    }

    @Test
    void handleSignUp_WhenUserIsNew_ShouldSaveUser() {
        // Arrange
        SignUpCommand command = new SignUpCommand("newUser", "new@email.com", "password", Collections.emptyList());
        Role userRole = new Role(Roles.ROLE_USER);

        when(userRepository.existsByUsername(command.username())).thenReturn(false);
        when(userRepository.existsByEmail(command.email())).thenReturn(false);
        when(roleRepository.findByName(Roles.ROLE_USER)).thenReturn(Optional.of(userRole));
        when(hashingService.encode(command.password())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Optional<User> result = userCommandService.handle(command);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("encodedPassword", result.get().getPassword());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void handleSignIn_WhenCredentialsAreValid_ShouldReturnUserAndToken() {
        // Arrange
        String username = "validUser";
        String password = "password";
        String encodedPassword = "encodedPassword";
        String token = "jwt-token";

        User user = new User(username, "email@test.com", encodedPassword, List.of(new Role(Roles.ROLE_USER)));
        SignInCommand command = new SignInCommand(username, password);

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(hashingService.matches(password, encodedPassword)).thenReturn(true);
        when(tokenService.generateToken(any(Authentication.class))).thenReturn(token);

        // Act
        Optional<ImmutablePair<User, String>> result = userCommandService.handle(command);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(token, result.get().getRight());
        assertEquals(username, result.get().getLeft().getUsername());
    }

    @Test
    void handleSignIn_WhenPasswordInvalid_ShouldThrowException() {
        // Arrange
        String username = "validUser";
        User user = new User(username, "email", "encoded", List.of());
        SignInCommand command = new SignInCommand(username, "wrongPassword");

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(hashingService.matches("wrongPassword", "encoded")).thenReturn(false);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> userCommandService.handle(command));
    }
}