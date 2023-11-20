package com.example.rentacaripg.services;

import com.example.rentacaripg.model.entities.User;
import com.example.rentacaripg.model.entities.UserRole;
import com.example.rentacaripg.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class RentACarUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public RentACarUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return userRepository.findByEmail(email)
                .map(RentACarUserDetailsService::map)
                .orElseThrow(() -> new UsernameNotFoundException("User " + email + " not found!"));
    }

    private static UserDetails map(User userEntity) {

        return org.springframework.security.core.userdetails.User
                .withUsername(userEntity.getEmail())
                .password(userEntity.getPassword())
                .authorities(userEntity.getRoles().stream().map(RentACarUserDetailsService::map).toList())
                .build();
    }

    private static GrantedAuthority map(UserRole userRole) {
        return new SimpleGrantedAuthority(
                "ROLE_" + userRole.getRole().name());
    }
}
