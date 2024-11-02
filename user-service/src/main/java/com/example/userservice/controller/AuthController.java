package com.example.userservice.controller;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.entity.User;
import com.example.userservice.model.AuthRequest;
import com.example.userservice.model.AuthResponse;
import com.example.userservice.producer.MessageProducer;
import com.example.userservice.service.AuthService;
import com.example.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private MessageProducer messageProducer;

    private final UserService userService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest authRequest) {
        // Authenticate and generate JWT token
        return authService.authenticateAndGenerateToken(authRequest);
    }

    @PostMapping("/register")
    public String register(@RequestBody UserDTO userDTO) {
        try {
            User user = userService.registerUser(userDTO);
            messageProducer.send("user-register-topic", userDTO.toString());
            return "User registered successfully: "  + user.getUsername();
        } catch (Exception e) {
            messageProducer.send("any-topic", "User registration Failed");
            return "Error: " + e.getMessage();
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam String token) {
        authService.validateToken(token);
        return "Token is valid";
    }
}
