package com.pharmacoldtrack.platform.iam.domain.model.aggregates;

import com.pharmacoldtrack.platform.iam.domain.model.entities.Role;
import com.pharmacoldtrack.platform.iam.domain.model.valueobjects.AccountStatus;
import com.pharmacoldtrack.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class User extends AuditableAbstractAggregateRoot<User> {

    @NotBlank
    @Size(max = 50)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    private Integer failedLoginAttempts = 0;
    private LocalDateTime lastLoginAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {
        this.accountStatus = AccountStatus.PENDING_ACTIVATION;
        this.roles = new HashSet<>();
    }

    public User(String username, String email, String password) {
        this();
        this.username = username;
        this.email = email;
        this.password = password;
        this.accountStatus = AccountStatus.ACTIVE;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }
}