package com.foodpanda.order.producer;

import com.foodpanda.shared.event.OrderEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {
    @Autowired
    private NewTopic newTopic;

    @Autowired
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public void publish(OrderEvent orderEvent) {
        Message<OrderEvent> message = MessageBuilder
                .withPayload(orderEvent)
                .setHeader(KafkaHeaders.TOPIC, newTopic.name())
                .build();

        kafkaTemplate.send(message);
    }
}
