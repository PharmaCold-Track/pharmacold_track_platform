package com.pharmacoldtrack.platform.iam.domain.model.commands;

/**
 * Command used to trigger the seeding of default roles into the database.
 * Usually executed at application startup.
 */
public record SeedRolesCommand() {
}