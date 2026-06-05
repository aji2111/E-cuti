package com.ecuti.auth_service.controller;

import com.ecuti.auth_service.dto.request.RegisterRequest;
import com.ecuti.auth_service.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Endpoint Registrasi
    @PostMapping("/api/auth/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request) {
        authService.register(request);
        return new ResponseEntity<>("User registered successfully!", HttpStatus.CREATED);
    }

}