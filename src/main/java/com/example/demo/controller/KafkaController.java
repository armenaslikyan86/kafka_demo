package com.example.demo.controller;

import com.example.demo.service.KafkaConsumer;
import com.example.demo.service.KafkaProducer;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    private final KafkaConsumer consumer;
    private final KafkaProducer producer;

    @Autowired
    public KafkaController(KafkaConsumer consumer, KafkaProducer producer) {
        this.consumer = consumer;
        this.producer = producer;
    }

    @PostMapping("/send")
    public ResponseEntity<Void> send(@RequestBody String data) {
        producer.produce(data);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/receive")
    public ResponseEntity<List<String>> receive() {
        return ResponseEntity.ok(consumer.getMessages());
    }
}