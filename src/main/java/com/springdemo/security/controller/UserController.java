package com.springdemo.security.controller;

import com.springdemo.security.model.User;
import com.springdemo.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing users.
 *
 * <p>
 * Updates as of 2025-07-28:
 * - Added @PreAuthorize annotations for endpoint-level access control
 * - Most endpoints are now public (permitAll)
 * - Only update and delete require ADMIN role
 * </p>
 */
@RestController
@RequestMapping("/users")
public class UserController {
    /**
     * The service layer dependency for user operations.
     */
    private final UserService userService;

    /**
     * Constructor-based dependency injection for the UserService instance.
     *
     * @param userService the UserService instance to be injected
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Endpoint to retrieve a list of all users.
     * Accessible via HTTP GET at /users
     *
     * @return list of User objects
     */
    @GetMapping
    @PreAuthorize("permitAll")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Endpoint to retrieve a user by their ID.
     * Accessible via HTTP GET at /users/{id}
     *
     * @param id the ID of the user to be retrieved
     * @return ResponseEntity containing the User object, or a 404 response if not found
     */
    @GetMapping("/{id}")
    @PreAuthorize("permitAll")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    /**
     * Endpoint to create a new user.
     * Accessible via HTTP POST at /users
     *
     * @param user the User object to be created
     * @return the created User object
     */
    @PostMapping
    @PreAuthorize("permitAll")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    /**
     * Endpoint to update an existing user.
     * Accessible via HTTP PUT at /users/{id}
     *
     * @param id   the ID of the user to be updated
     * @param user the updated User object
     * @return ResponseEntity containing the updated User object, or a 404 response if not found
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updated = userService.updateUser(id, user);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    /**
     * Endpoint to delete a user by their ID.
     * Accessible via HTTP DELETE at /users/{id}
     *
     * @param id the ID of the user to be deleted
     * @return ResponseEntity with a 204 status code if deleted successfully, or a 404 response if not found
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.deleteUser(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
