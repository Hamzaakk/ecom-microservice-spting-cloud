package com.ecommerceapp.notificationservice.config;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListner {
    @KafkaListener(topics = "notificationTopic", groupId = "notificationId")
    public void listen(String message) {
        System.out.println("Received message: " + message);
    }

}
