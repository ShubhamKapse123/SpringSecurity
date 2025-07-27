package com.springdemo.security.service;

import com.springdemo.security.model.User;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {
    private final Map<Long, User> users = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public User getUserById(Long id) {
        return users.get(id);
    }

    public User createUser(User user) {
        long id = idCounter.incrementAndGet();
        user.setId(id);
        users.put(id, user);
        return user;
    }

    public User updateUser(Long id, User user) {
        if (!users.containsKey(id)) {
            return null;
        }
        user.setId(id);
        users.put(id, user);
        return user;
    }

    public boolean deleteUser(Long id) {
        return users.remove(id) != null;
    }
}
