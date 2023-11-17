package com.example.rentacaripg.model.dtos;

import com.example.rentacaripg.validation.FieldMatch;
import com.example.rentacaripg.validation.UniqueUserEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@FieldMatch(
        first = "password",
        second = "confirmPassword",
        message = "The passwords should match"
)
public class UserRegistrationDTO {

    @NotEmpty(message = "Name cannot be empty")
    private String firstName;
    @NotEmpty(message = "Last name cannot be empty")
    private String lastName;
    @NotEmpty(message = "Email cannot by empty")
    @NotNull
    @Email
    @UniqueUserEmail
    private String email;
    @NotEmpty(message = "Password cannot be empty")
    private String password;
    @NotEmpty(message = "Confirm password cannot be empty")
    private String confirmPassword;

    public UserRegistrationDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public UserRegistrationDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegistrationDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegistrationDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegistrationDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegistrationDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
