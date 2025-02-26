package org.example.debezium.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

public class KafkaConfig {
    @Bean
    public NewTopic j2osTopic() {
        return TopicBuilder.name("j2os")
                .build();
    }
    @Bean
    public NewTopic sharifTopic() {
        return TopicBuilder.name("sharif")
                .build();
    }
}
