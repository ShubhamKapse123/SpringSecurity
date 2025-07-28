package com.springdemo.security.service;

import com.springdemo.security.model.CustomUserDetails;
import com.springdemo.security.model.User;

import com.springdemo.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserService implements UserDetailsService {
    @Autowired
    public UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=  userRepository.findByUsername(username);
        if(user==null){
        throw new UsernameNotFoundException("NO USER");
        }
        return new CustomUserDetails(user);
    }
}
