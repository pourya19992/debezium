package org.example.debezium.config;

public class MongoDbConnector implements DatabaseConnector {
    private final MongoDbConfig config;

    public MongoDbConnector(MongoDbConfig config) {
        this.config = config;
    }

    @Override
    public void connect() {
        // اتصال به MongoDB
    }

    @Override
    public void save(Object data) {
        // ذخیره در MongoDB
    }

    @Override
    public Object fetch(String query) {
        // بازیابی از MongoDB
        return null;
    }
}
