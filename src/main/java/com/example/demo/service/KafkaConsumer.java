package com.example.demo.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
public class KafkaConsumer {

    public static List<String> messages = new ArrayList<>();
    private final static String topic = "armen_test";
    private final static String groupId = "group";

    @KafkaListener(topics = topic, groupId = groupId)
    public void listen(String message) {
        messages.add(message);
    }
}
