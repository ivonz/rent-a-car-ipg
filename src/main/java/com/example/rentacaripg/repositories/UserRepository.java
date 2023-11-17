package com.example.rentacaripg.repositories;

import com.example.rentacaripg.model.dtos.UserRegistrationDTO;
import com.example.rentacaripg.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String value);
}
