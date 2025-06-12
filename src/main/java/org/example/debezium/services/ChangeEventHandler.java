package org.example.debezium.services;

import io.debezium.engine.ChangeEvent;

public interface ChangeEventHandler<T> {
    void handleChangeEvent(ChangeEvent<String, T> event);
}
