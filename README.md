# Spring Security User CRUD API

## Project Overview

This project is a simple Spring Boot REST API for user management, demonstrating CRUD operations (Create, Read, Update, Delete) for a User entity. Instead of a database, it uses an in-memory collection (Map) to store users. The project is structured with a model, service layer, and controller, and integrates Spring Security for authentication and authorization.

---

## How Spring Security Works (Step by Step)

1. **Add Dependency**
   - The `spring-boot-starter-security` dependency is included in `pom.xml`. This activates Spring Security in your project.

2. **Default Security Behavior**
   - By default, Spring Security secures all endpoints. When you start the application, all HTTP endpoints require authentication (basic auth with a generated password).

3. **Authentication**
   - When you access any API endpoint, Spring Security intercepts the request and checks for valid credentials (username/password).
   - If credentials are missing or invalid, it returns a 401 Unauthorized response.
   - By default, Spring Security creates a user with username `user` and a random password (shown in the console on startup).

4. **Authorization**
   - After authentication, Spring Security can restrict access to endpoints based on roles or authorities.
   - You can customize which users/roles can access which endpoints by configuring a security configuration class.

5. **Customizing Security**
   - You can define your own security rules by creating a class that extends `WebSecurityConfigurerAdapter` (Spring Boot 2.x) or using `SecurityFilterChain` bean (Spring Boot 3.x).
   - Here, you can specify which endpoints are public, which require authentication, and set up custom login/logout behavior.

6. **Session Management**
   - By default, Spring Security creates a session for each authenticated user. You can configure it for stateless APIs (using JWT or similar) if needed.

7. **Further Customization**
   - You can implement custom user details, password encoding, and integrate with databases or other authentication providers as your project grows.

---

## Example Flow in This Project

1. Start the application.
2. Try to access `/users` endpoint. Spring Security will prompt for username/password.
3. Use the default credentials or configure your own.
4. Once authenticated, you can perform CRUD operations on users via REST endpoints.
5. You can later customize security to allow/deny access to certain endpoints based on roles or user details.

---

Let me know if you want a sample security configuration file or further explanation on customizing Spring Security!

---

## Spring Security Authentication Cycle (Request Flow)

1. **Request Enters Security Filter Chain**
   - Every HTTP request passes through a chain of security filters (e.g., `UsernamePasswordAuthenticationFilter`).

2. **Authentication Manager**
   - The filter extracts credentials and sends them to the `AuthenticationManager`.

3. **Provider Manager**
   - The `AuthenticationManager` delegates to one or more `AuthenticationProvider`s (via `ProviderManager`).

4. **Authentication Provider**
   - Each `AuthenticationProvider` attempts to authenticate the credentials (e.g., using a database, in-memory, LDAP, etc.).
   - If successful, it returns an `Authentication` object containing user details and authorities.

5. **Security Context**
   - On successful authentication, the `Authentication` object is stored in the `SecurityContextHolder` (thread-local storage).

6. **Access Decision**
   - The request proceeds. Spring Security checks the user's authorities/roles against endpoint requirements (authorization).

7. **Response**
   - If authorized, the request is processed by the controller. Otherwise, a 403 Forbidden or 401 Unauthorized response is returned.

---

This cycle ensures every request is authenticated and authorized before accessing protected resources.
