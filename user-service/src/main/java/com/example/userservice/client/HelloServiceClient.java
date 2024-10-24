package com.example.userservice.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloServiceClient {
    private final RestTemplate restTemplate;

    public HelloServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String sendHelloEmail() {
        return restTemplate.getForObject("http://hello-service/api/hello/send-email", String.class);
    }
}
