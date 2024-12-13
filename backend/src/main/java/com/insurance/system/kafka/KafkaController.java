package com.insurance.system.kafka;

import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/kafka")
public class KafkaController {
  private final KafkaProducer kafkaProducer;
  public KafkaController(KafkaProducer kafkaProducer) {
    this.kafkaProducer = kafkaProducer;
  }

  @PostMapping("/publish")
  public String publishMessage(@RequestParam("message") String message) {
    try {
      kafkaProducer.sendMessage("test-topic", message);
      return "Message published";
    } catch (Exception e) {
      return "Failed to publish message: " + e.getMessage();
    }
  }
}
