package com.example.helloservice.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationMessageConsumer {

    @KafkaListener(topics = "user-register-topic", groupId = "user-group")
    public void listen(String message) {
        System.out.println(message);
        System.out.println(message);
    }

    @KafkaListener(topics = "any-topic", groupId = "user-group")
    public void listenAny(String message) {
        System.out.println(message);
    }
}
