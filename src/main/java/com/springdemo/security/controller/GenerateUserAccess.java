package com.springdemo.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for generating user credentials and access endpoints.
 *
 * <p>
 * Updates as of 2025-07-28:
 * - Added @PreAuthorize("permitAll") to allow public access to credential generation endpoint
 * </p>
 */
@RestController
@RequestMapping("/generateAccess/")
public class GenerateUserAccess {
    /**
     * Endpoint to generate a random username and password.
     * Accessible via HTTP GET at /generateAccess/basicAuth
     *
     * @return String containing generated username and password
     */
    @GetMapping("basicAuth")
    @PreAuthorize("permitAll")
    public String generateUserPass() {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int index = (int) (Math.random() * alphabet.length());
            sb.append(alphabet.charAt(index));
        }
        return "username: user" + sb + " password: " + sb.toString();
    }
}
