package com.example.rentacaripg.validation;

import com.example.rentacaripg.model.entities.User;
import com.example.rentacaripg.repositories.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Optional;

public class UniqueUserEmailValidator implements ConstraintValidator<UniqueUserEmail, String>{

    private final UserRepository userRepository;

    public UniqueUserEmailValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Optional<User> optUser = userRepository.findByEmail(value);

        if (optUser.isPresent()) {
            return false;
        }

        return true;
    }
}
