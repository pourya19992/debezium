package org.example.debezium.config;

public class MongoDbConnector implements DatabaseConnector {
    private final MongoDbConfig config;

    public MongoDbConnector(MongoDbConfig config) {
        this.config = config;
    }

    @Override
    public void connect() {
    }

    @Override
    public void save(Object data) {
    }

    @Override
    public Object fetch(String query) {
        return null;
    }
}
