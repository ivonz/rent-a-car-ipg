package com.example.rentacaripg.config;

import com.example.rentacaripg.model.enums.UserRoleEnum;
import com.example.rentacaripg.repositories.UserRepository;
import com.example.rentacaripg.services.RentACarUserDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    private final String rememberMeKey;

    public SecurityConfiguration(@Value("$remember.me.key") String rememberMeKey) {
        this.rememberMeKey = rememberMeKey;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests(
            //Define which urls are visible by users
            authorizeRequests -> authorizeRequests
                    // All static resources which are situated in js, images, css are available for anyone
                    .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                    // Allow anyone to see the home page, the registration page and the login form
                    .requestMatchers("/", "/users/login", "/users/register", "/users/login-error").permitAll()
                    .requestMatchers("/admin").hasRole(UserRoleEnum.ADMIN.name())
                    // All other requests are authenticated
                    .anyRequest().authenticated()
        ).formLogin(
            formLogin -> formLogin
                    // redirect here when we access something which is not allowed.
                    // also this is the page where we perform login.
                    .loginPage("/users/login")
                    // the names of the input fields (in our case in auth-login.html)
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/")
                    .failureForwardUrl("/users/login-error")
        ).logout(
                logout -> logout
                        //the URL where we should POST something in order to perform the logout
                        .logoutUrl("/users/logout")
                        //where to go when logout
                        .logoutSuccessUrl("/")
                        //invalidate the HTTP session
                        .invalidateHttpSession(true)
        ).rememberMe(
                rememberMe -> {
                    rememberMe.key(rememberMeKey)
                            //name param from auth-login checkbox
                            .rememberMeParameter("rememberme")
                            .rememberMeCookieName("rememberme")
                            .tokenValiditySeconds(60);
                }
        ).build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        // This service translates the RentACar users and roles
        // to representations which spring security understands.
        return new RentACarUserDetailsService(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }

}
