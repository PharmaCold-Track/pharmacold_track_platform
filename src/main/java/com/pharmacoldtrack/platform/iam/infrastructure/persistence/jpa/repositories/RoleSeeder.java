package com.pharmacoldtrack.platform.iam.infrastructure.persistence.jpa.repositories;

import com.pharmacoldtrack.platform.iam.domain.model.entities.Role;
import com.pharmacoldtrack.platform.iam.domain.model.valueobjects.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class RoleSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        Arrays.stream(Roles.values()).forEach(roleName -> {
            if (!roleRepository.existsByName(roleName)) {
                roleRepository.save(new Role(roleName));
                System.out.println("Role initialized: " + roleName);
            }
        });
    }
}