package com.example.helloservice.controller;

import com.example.helloservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hello")
public class HelloController {

    private final Environment environment;
    private final EmailService emailService;

    @GetMapping("/welcome")
    public String testProp() {
        return "welcome from hello service tfddfvdf";
    }

    @GetMapping("/send-email")
    public String sendEmail() {
        return emailService.sendEmail();
    }
}
