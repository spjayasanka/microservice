package com.example.userservice.service;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.entity.User;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public User registerUser(UserDTO userDTO) throws Exception {
        // Check if user already exists
        if (userRepository.findByUsername(userDTO.getUsername()) != null) {
            throw new Exception("User already exists");
        }

        // Create new user and save it
        User newUser = new User();
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(passwordEncoder.encode(userDTO.getPassword())); // Encrypt the password
        newUser.setName(userDTO.getName());
        newUser.setDescription(userDTO.getDescription());

        return userRepository.save(newUser);
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
