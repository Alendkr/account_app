package com.example.account_app.config;

import io.ebean.Database;
import io.ebean.DatabaseFactory;
import io.ebean.config.DatabaseConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EbeanConfig {

    @Bean
    public Database ebeanDatabase() {
        DatabaseConfig config = new DatabaseConfig();
        config.setName("db");
        config.loadFromProperties();

        return DatabaseFactory.create(config);
    }
}
