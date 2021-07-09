package com.example.security.auth;

import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface ApplicationUserRepository {
    public Optional<ApplicationUser> getApplicationUSerByUsername(String username);
}
