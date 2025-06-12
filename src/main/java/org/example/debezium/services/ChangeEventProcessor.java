package org.example.debezium.services;

import io.debezium.engine.ChangeEvent;
import org.springframework.stereotype.Component;

@Component
public class ChangeEventProcessor implements ChangeEventHandler<String> {

    @Override
    public void handleChangeEvent(ChangeEvent<String, String> event) {

        System.out.println("Event received: " + event);

        String message = "Processed event: " + event.toString();

    }
}
