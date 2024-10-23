package com.example.testservice.controller;

import com.example.testservice.client.HelloServiceClient;
import com.example.testservice.entity.User;
import com.example.testservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private Environment environment;

    private final UserRepository userRepository;
    private final HelloServiceClient helloServiceClient;

    public TestController(UserRepository userRepository, HelloServiceClient helloServiceClient) {
        this.userRepository = userRepository;
        this.helloServiceClient = helloServiceClient;
    }

    @GetMapping("/send-email-from-test")
    public String sendEmailFromTest() {
        return helloServiceClient.sendHelloEmail();
    }

    @GetMapping("/welcome")
    public String testProp() {
        String port = environment.getProperty("local.server.port");
        return "Application is running on port: " + port;
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user) {
        return userRepository.save(user);
    }

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

}

