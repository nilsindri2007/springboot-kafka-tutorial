package com.example.springbootkafkatutorial.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaProducer {

    private static final Logger LOG= LoggerFactory.getLogger(JsonKafkaProducer.class);

    private KafkaTemplate<String, SecurityProperties.User> kafkaTemplate;

    public JsonKafkaProducer(KafkaTemplate<String, SecurityProperties.User> kafkaTemplate) {

        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage( SecurityProperties.User user){
        LOG.info(String.format("Message sent =.",user.toString()));
        Message<SecurityProperties.User> message= MessageBuilder
                .withPayload(user)
                .setHeader(KafkaHeaders.TOPIC, "topic_demo")
                .build();
        kafkaTemplate.send(message);
    }
}
