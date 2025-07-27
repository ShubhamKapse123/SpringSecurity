package com.springdemo.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for Spring Security Demo.
 *
 * <p>
 * Annotated with {@link SpringBootApplication} to enable auto-configuration, component scanning,
 * and to mark this as the starting point of the Spring Boot application.
 * </p>
 */
@SpringBootApplication
public class SpringSecurityApplication {
    /**
     * Main method to launch the Spring Boot application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        System.out.println("Secure Web Application");
        SpringApplication.run(SpringSecurityApplication.class, args);
    }
}
