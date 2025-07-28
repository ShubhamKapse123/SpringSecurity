package com.springdemo.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import javax.servlet.http.Cookie;

/**
 * Configuration class for Spring Security settings.
 *
 * <p>
 * Updates as of 2025-07-28:
 * - Enabled method-level security with @EnableGlobalMethodSecurity(prePostEnabled = true)
 * - Disabled CSRF protection for all endpoints
 * - Public access allowed for /generateAccess/** and /users/** endpoints
 * - All other endpoints require authentication
 * </p>
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * Configures HTTP security for the application.
     *
     * @param http the {@link HttpSecurity} to modify
     * @throws Exception if an error occurs
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                //.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
                .authorizeRequests()
                .antMatchers( "/generateAccess/**").permitAll()
                .antMatchers( "/users/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                //.formLogin()
                .httpBasic();
    }

    /**
     * Configures in-memory authentication for the application.
     *
     * @param auth the {@link AuthenticationManagerBuilder} to modify
     * @throws Exception if an error occurs
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("TestUser")
                .password(passwordEncoder().encode("123**"))
                .roles("NORMAL");
        auth.inMemoryAuthentication()
                .withUser("AdminUser")
                .password(passwordEncoder().encode("admin@123"))
                .roles("ADMIN");
    }

    /**
     * Provides a PasswordEncoder bean that uses BCrypt hashing algorithm.
     * This encoder is used to hash passwords securely.
     *
     * @return a BCryptPasswordEncoder instance
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
