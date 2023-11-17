package com.example.rentacaripg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class RentACarIpgApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentACarIpgApplication.class, args);
    }

}
