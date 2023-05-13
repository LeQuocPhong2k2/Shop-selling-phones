package com.iuh.server.controller;

import com.iuh.server.model.entity.Account;
import com.iuh.server.model.request.AuthenticationRequest;
import com.iuh.server.model.request.RegisterRequest;
import com.iuh.server.model.response.AuthenticationResponse;
import com.iuh.server.service.AuthenticationService;
import com.iuh.server.service.impl.UserServiceImpl;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService service;

    @Autowired
    UserServiceImpl userServiceImpl;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "public/Login";
    }

    @GetMapping("/register")
    public String openRegister() {
        return "public/SignUp";
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        Account account = userServiceImpl.findByEmail(request.getEmail()).orElse(null);
        if (account != null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(service.authenticate(authenticationRequest));
    }
}
