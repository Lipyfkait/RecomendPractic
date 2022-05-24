package ru.ithub.examination.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.ithub.examination.core.router.Router;
import ru.ithub.examination.payload.ApiResponse;
import ru.ithub.examination.payload.request.LoginRequest;
import ru.ithub.examination.payload.request.RegistrationRequest;
import ru.ithub.examination.payload.response.AuthenticationResponse;
import ru.ithub.examination.services.AuthenticationService;

import javax.validation.Valid;

@CrossOrigin(value = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService service;

    @PostMapping(Router.Authentication.Sign.In.ROOT)
    private ApiResponse<AuthenticationResponse> signIn(@Valid @RequestBody LoginRequest request) {
        return ApiResponse.success(service.authenticate(request));
    }

    @PostMapping(Router.Authentication.Sign.Up.ROOT)
    private ApiResponse<AuthenticationResponse> signUp(@Valid @RequestBody RegistrationRequest request) {
        return ApiResponse.success(service.register(request));
    }
}
