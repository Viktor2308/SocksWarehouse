package com.example.sockswarehouse.config.init;

import io.r2dbc.spi.ConnectionFactory;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

@Configuration
@Log
public class DatabaseInitializer {

    @Value("${initialize-schema}")
    private boolean initiaLizeSchema;

    @Bean
    public ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
        log.info("Initializing database "+initiaLizeSchema);
        if(!initiaLizeSchema){
            return null;
        }
        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);

        CompositeDatabasePopulator populator = new CompositeDatabasePopulator();
        populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("db/changelog/changelog.sql")));
        initializer.setDatabasePopulator(populator);

        return initializer;
    }
}
