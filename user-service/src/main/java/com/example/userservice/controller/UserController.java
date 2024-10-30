package com.example.userservice.controller;

import com.example.userservice.client.HelloServiceClient;
import com.example.userservice.dto.UserDTO;
import com.example.userservice.entity.User;
import com.example.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private Environment environment;

    private final UserRepository userRepository;
    private final HelloServiceClient helloServiceClient;

    public UserController(UserRepository userRepository, HelloServiceClient helloServiceClient) {
        this.userRepository = userRepository;
        this.helloServiceClient = helloServiceClient;
    }

    @GetMapping("/send-email-from-test")
    public String sendEmailFromTest() {
        return helloServiceClient.sendHelloEmail();
    }

//    @PostMapping("/save-user")
//    public User addUser(@RequestBody UserDTO user) {
//        return userRepository.save(user);
//    }

    @GetMapping("/users")
    public Collection<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable int id) {
        return userRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    @GetMapping("/welcome")
    public String testProp() {
        String port = environment.getProperty("local.server.port");
        return "Application is running on port: " + port;
    }

}

