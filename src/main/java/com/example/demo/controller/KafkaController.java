package com.example.demo.controller;

import com.example.demo.service.KafkaConsumer;
import com.example.demo.service.KafkaProducer;
import org.springframework.web.bind.annotation.*;



import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
public class KafkaController {
    @Autowired
    private KafkaConsumer consumer;

    @Autowired
    private KafkaProducer producer;

    @PostMapping("/send")
    public void send(@RequestBody String data) {
        producer.produce(data);
    }
    @GetMapping("/receive")
    public List<String> receive() {
        return consumer.messages;
    }

    public KafkaConsumer getConsumer() {
        return consumer;
    }

    public KafkaProducer getProducer() {
        return producer;
    }

    public void setConsumer(KafkaConsumer consumer) {
        this.consumer = consumer;
    }

    public void setProducer(KafkaProducer producer) {
        this.producer = producer;
    }
}