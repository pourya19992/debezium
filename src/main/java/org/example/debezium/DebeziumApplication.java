package org.example.debezium;

import org.example.debezium.config.DebeziumProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(DebeziumProperties.class)
public class DebeziumApplication {

    public static void main(String[] args) {
        SpringApplication.run(DebeziumApplication.class, args);
    }
}
