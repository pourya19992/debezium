package org.example.debezium.config;

import io.debezium.engine.ChangeEvent;
import io.debezium.engine.DebeziumEngine;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class DebeziumRunner {

    @Autowired
    public DebeziumRunner(DebeziumEngine<ChangeEvent<String, String>> debeziumEngine) {
    }

    @PostConstruct
    public void start() {
        System.out.println("Debezium engine is running...");
    }
}
