package com.example.rentacaripg.services.impl;

import com.example.rentacaripg.model.dtos.UserRegistrationDTO;
import com.example.rentacaripg.model.entities.User;
import com.example.rentacaripg.repositories.UserRepository;
import com.example.rentacaripg.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerUser(UserRegistrationDTO userRegistrationDTO) {
        userRepository.save(map(userRegistrationDTO));
    }

    private User map(UserRegistrationDTO userRegistrationDTO) {
        return new User()
                .setFirstName(userRegistrationDTO.getFirstName())
                .setLastName(userRegistrationDTO.getLastName())
                .setEmail(userRegistrationDTO.getEmail())
                .setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
    }
}
