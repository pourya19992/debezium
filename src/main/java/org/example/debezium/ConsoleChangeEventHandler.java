package org.example.debezium;

import io.debezium.engine.ChangeEvent;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;

@Component
public class ConsoleChangeEventHandler implements ChangeEventHandler<String>{
    private static final Logger log = LoggerFactory.getLogger(ConsoleChangeEventHandler.class);

    @Override
    public void handleChangeEvent(ChangeEvent<String, String> event) {
        String key = event.key();
        String value = event.value();
        String operation = event.destination();
        log.info("Change Event Received:");
        log.info("Key: {}", key);
        log.info("Value: {}", value);
        log.info("Operation: {}", operation);
    }

}
