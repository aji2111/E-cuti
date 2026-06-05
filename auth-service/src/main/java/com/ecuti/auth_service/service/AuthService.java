package com.ecuti.auth_service.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecuti.auth_service.dto.request.RegisterRequest;
import com.ecuti.auth_service.entity.RoleType;
import com.ecuti.auth_service.entity.User;
import com.ecuti.auth_service.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(RegisterRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

     User user = new User();
user.setUsername(request.getUsername());
user.setEmail(request.getEmail());
user.setPassword(passwordEncoder.encode(request.getPassword()));
user.setRole(RoleType.EMPLOYEE);

userRepository.save(user);
    }
}
