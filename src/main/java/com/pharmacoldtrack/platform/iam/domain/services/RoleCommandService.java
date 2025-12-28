package com.pharmacoldtrack.platform.iam.domain.services;

import com.pharmacoldtrack.platform.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {

    /**
     * Handles the process of seeding initial roles in the system.
     * This ensures that essential roles exist before users are created.
     * * @param command The seed command trigger
     */
    void handle(SeedRolesCommand command);
}