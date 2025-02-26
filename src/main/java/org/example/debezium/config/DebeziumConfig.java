package org.example.debezium.config;


import io.debezium.engine.ChangeEvent;
import io.debezium.engine.DebeziumEngine;
import io.debezium.engine.format.Json;
import org.example.debezium.ChangeEventHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
public class DebeziumConfig {

    private final List<ChangeEventHandler<String>> eventHandlers;
    private final DebeziumProperties debeziumProperties;

    public DebeziumConfig(List<ChangeEventHandler<String>> eventHandlers, DebeziumProperties debeziumProperties) {
        this.eventHandlers = eventHandlers;
        this.debeziumProperties = debeziumProperties;
    }

    @Bean
    public DebeziumEngine<ChangeEvent<String, String>> debeziumEngine() {
        Properties props = debeziumProperties.toProperties();

        props.setProperty("offset.storage", "org.apache.kafka.connect.storage.MemoryOffsetBackingStore");
        DebeziumEngine<ChangeEvent<String, String>> engine = DebeziumEngine.create(Json.class)
                .using(props)
                .notifying(event -> {
                    for (ChangeEventHandler<String> handler : eventHandlers) {
                        handler.handleChangeEvent(event);
                    }
                })
                .build();

        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(engine);

        return engine;
    }
}