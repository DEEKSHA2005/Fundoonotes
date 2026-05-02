package com.fundoo.notes.service;

import com.fundoo.notes.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQConsumer {

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void receive(String message) {
        log.info("Message Received: {}", message);
    }
}