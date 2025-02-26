package org.example.debezium.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
@ConfigurationProperties(prefix = "debezium.engine")
@Data
public class DebeziumProperties {

    private String name;
    private String connectorClass;
    private String offsetStorage;
    private String offsetStorageFileFilename;
    private long offsetFlushIntervalMs;
    private DatabaseProperties database;
    private String databaseHistory;
    private String databaseHistoryFileFilename;
    private String tableIncludeList;

    public Properties toProperties() {
        Properties props = new Properties();
        props.setProperty("name", name);
        props.setProperty("connector.class", connectorClass);
        props.setProperty("offset.storage", offsetStorage);
        props.setProperty("offset.storage.file.filename", offsetStorageFileFilename);
        props.setProperty("offset.flush.interval.ms", String.valueOf(offsetFlushIntervalMs));
        props.setProperty("database.hostname", database.getHostname());
        props.setProperty("database.port", String.valueOf(database.getPort()));
        props.setProperty("database.user", database.getUser());
        props.setProperty("database.password", database.getPassword());
        props.setProperty("database.dbname", database.getDbname());
        props.setProperty("database.server.name", database.getServerName());
        props.setProperty("database.history", databaseHistory);
        props.setProperty("database.history.file.filename", databaseHistoryFileFilename);
        props.setProperty("table.include.list", tableIncludeList);
        return props;
    }

    @Data
    public static class DatabaseProperties {
        private String hostname;
        private int port;
        private String user;
        private String password;
        private String dbname;
        private String serverName;

    }

}