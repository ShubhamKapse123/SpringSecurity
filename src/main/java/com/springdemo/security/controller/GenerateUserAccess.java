package com.springdemo.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for generating user access credentials.
 *
 * <p>
 * Annotated with {@link RestController} to indicate that this class handles REST API requests.
 * {@link RequestMapping} sets the base URL path for all endpoints in this controller.
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
