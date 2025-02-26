package org.example.debezium.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
public class DatabaseFactory {

    @Bean
    @Profile("mongodb")
    public MongoDbConfig mongoDbConfig() {
        return new MongoDbConfig();
    }

    @Bean
    @Profile("oracle")
    public OracleDbConfig oracleDbConfig() {
        return new OracleDbConfig();
    }

    @Bean
    @Profile("sqlserver")
    public SqlServerConfig sqlServerConfig() {
        return new SqlServerConfig();
    }
}