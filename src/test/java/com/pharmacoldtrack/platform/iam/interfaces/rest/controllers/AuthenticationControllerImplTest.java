package com.pharmacoldtrack.platform.iam.interfaces.rest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pharmacoldtrack.platform.iam.domain.model.aggregates.User;
import com.pharmacoldtrack.platform.iam.domain.model.commands.SignInCommand;
import com.pharmacoldtrack.platform.iam.domain.model.commands.SignUpCommand;
import com.pharmacoldtrack.platform.iam.domain.model.entities.Role;
import com.pharmacoldtrack.platform.iam.domain.model.valueobjects.Roles;
import com.pharmacoldtrack.platform.iam.domain.services.UserCommandService;
import com.pharmacoldtrack.platform.iam.infrastructure.hashing.bcrypt.BCryptHashingService;
import com.pharmacoldtrack.platform.iam.infrastructure.tokens.jwt.BearerTokenService;
import com.pharmacoldtrack.platform.iam.interfaces.rest.dto.request.SignInResource;
import com.pharmacoldtrack.platform.iam.interfaces.rest.dto.request.SignUpResource;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = AuthenticationControllerImpl.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthenticationControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserCommandService userCommandService;

    @MockBean
    private BearerTokenService tokenService;

    @MockBean
    private BCryptHashingService hashingService;

    @MockBean
    private UserDetailsService userDetailsService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void signUp_ShouldReturnCreated_WhenDataIsValid() throws Exception {
        // Arrange
        SignUpResource resource = new SignUpResource("newUser", "test@email.com", "password");
        User mockUser = new User("newUser", "test@email.com", "encodedPass", List.of(new Role(Roles.ROLE_USER)));
        mockUser.setId(1L);

        when(userCommandService.handle(any(SignUpCommand.class))).thenReturn(Optional.of(mockUser));

        // Act & Assert
        mockMvc.perform(post("/authentication/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(resource)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("newUser"))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void signIn_ShouldReturnOk_WhenCredentialsAreValid() throws Exception {
        // Arrange
        SignInResource resource = new SignInResource("existingUser", "password");
        User mockUser = new User("existingUser", "test@email.com", "encodedPass", List.of(new Role(Roles.ROLE_USER)));
        mockUser.setId(1L);
        String mockToken = "mock-jwt-token";

        when(userCommandService.handle(any(SignInCommand.class)))
                .thenReturn(Optional.of(ImmutablePair.of(mockUser, mockToken)));

        // Act & Assert
        mockMvc.perform(post("/authentication/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(resource)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("existingUser"))
                .andExpect(jsonPath("$.token").value(mockToken));
    }

    @Test
    void signIn_ShouldReturnBadRequest_WhenServiceReturnsEmpty() throws Exception {
        // Arrange
        SignInResource resource = new SignInResource("wrongUser", "wrongPass");
        when(userCommandService.handle(any(SignInCommand.class))).thenReturn(Optional.empty());

        // Act & Assert
        mockMvc.perform(post("/authentication/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(resource)))
                .andExpect(status().isBadRequest());
    }
}