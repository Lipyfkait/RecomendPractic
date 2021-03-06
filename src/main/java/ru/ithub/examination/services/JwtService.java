package ru.ithub.examination.services;

import org.springframework.security.core.Authentication;

public interface JwtService {
    boolean validateJwtToken(String jwt);

    String getUsernameFromToken(String jwt);

    String generateToken(Authentication authentication);
}
