package ru.ithub.examination.services;

import ru.ithub.examination.payload.request.LoginRequest;
import ru.ithub.examination.payload.request.RegistrationRequest;
import ru.ithub.examination.payload.response.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse authenticate(LoginRequest request);
    AuthenticationResponse register(RegistrationRequest request);
}
