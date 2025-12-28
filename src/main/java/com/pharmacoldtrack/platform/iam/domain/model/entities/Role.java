package com.pharmacoldtrack.platform.iam.domain.model.entities;

import com.pharmacoldtrack.platform.iam.domain.model.valueobjects.Roles;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@With
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Roles name;

    public Role(Roles name) {
        this.name = name;
    }

    public String getStringName() {
        return name.name();
    }

    public static Role getDefaultRole() {
        return new Role(Roles.ROLE_USER);
    }
}