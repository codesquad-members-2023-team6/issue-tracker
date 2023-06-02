package com.team6.issue_tracker.global.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.relational.core.mapping.event.BeforeSaveEvent;

@Slf4j
@Configuration
public class JdbcConfig extends AbstractJdbcConfiguration {

    @Bean
    ApplicationListener<BeforeSaveEvent<Object>> loggingBeforeSave() {
        return event -> {
            Object entity = event.getEntity();
            log.info("NEW SAVE = {}", entity);
        };
    }
}
