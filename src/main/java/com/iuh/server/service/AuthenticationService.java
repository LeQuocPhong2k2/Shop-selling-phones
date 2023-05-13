package com.iuh.server.service;

import com.iuh.server.model.entity.Account;
import com.iuh.server.model.entity.Role;
import com.iuh.server.model.request.AuthenticationRequest;
import com.iuh.server.model.request.RegisterRequest;
import com.iuh.server.model.response.AuthenticationResponse;
import com.iuh.server.service.impl.UserServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    @Autowired
    UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = Account.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userService.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userService.findByEmail(request.getEmail()).orElseThrow(null);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public String logout(HttpServletRequest request) {
        var token = request.getHeader("Authorization");
        jwtService.invalidate(token);
        return "Logout successfully";
    }

}
