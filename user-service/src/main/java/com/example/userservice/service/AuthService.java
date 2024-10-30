package com.example.userservice.service;

import com.example.userservice.config.JwtUtil;
import com.example.userservice.model.AuthRequest;
import com.example.userservice.model.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthResponse authenticateAndGenerateToken(AuthRequest authRequest) {
        // Authenticate the user
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        if (authenticate.isAuthenticated()) {
            return new AuthResponse(jwtUtil.generateToken(authRequest.getUsername()));
        } else {
            throw new BadCredentialsException("Bad credentials");
        }
        // If authentication is successful, generate the token
//        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

    }

    public void validateToken(String token) {
        jwtUtil.validateToken(token);
    }
}
