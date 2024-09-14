package com.foodpanda.notification.consume;

import com.foodpanda.shared.event.OrderEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderEventConsumer {
    @KafkaListener(
            topics = "order_topic",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(OrderEvent orderEvent) {
        System.out.println("Consumed order event: " + orderEvent);
    }
}
