package com.example.rentacaripg.model.entities;

import com.example.rentacaripg.model.enums.UserRoleEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class UserRole{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    public UserRoleEnum getRole() {
        return role;
    }

    public UserRole setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }
}
