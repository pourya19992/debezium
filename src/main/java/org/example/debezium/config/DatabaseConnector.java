package org.example.debezium.config;

public interface DatabaseConnector {
    void connect();
    void save(Object data);
    Object fetch(String query);
}
