package nl.craftsmen.orderservice.gateway.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaSimpleProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    KafkaSimpleProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    void sendMessage(String topicName, String message) {
        kafkaTemplate.send(topicName, message);
    }
}
