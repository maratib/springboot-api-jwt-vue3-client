package com.jp.logs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.db.ColumnMapping;
import org.apache.logging.log4j.core.appender.db.jdbc.ColumnConfig;
import org.apache.logging.log4j.core.appender.db.jdbc.JdbcAppender;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.logging.log4j.core.config.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class LogConfig {
    @Autowired
    private Environment env;

    @PostConstruct
    public void onStartUp() {
        final LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        final Configuration config = ctx.getConfiguration();
        String url = env.getProperty("spring.datasource.url");
        String userName = env.getProperty("spring.datasource.username");
        String password = env.getProperty("spring.datasource.password");
        String validationQuery = env.getProperty("spring.datasource.validation-query");

        // Create a new connectionSource build from the Spring properties
        JdbcConnectionSource connectionSource = new JdbcConnectionSource(url,
                userName, password, validationQuery);

        ColumnConfig[] columnConfigs = new ColumnConfig[] {
                // ColumnConfig.newBuilder().setName("CREATED").setEventTimestamp(true).build(),
                ColumnConfig.newBuilder().setName("ref").setPattern("%logger").build(),
                ColumnConfig.newBuilder().setName("level").setPattern("%level").build(),
                ColumnConfig.newBuilder().setName("message").setPattern("%message").build(),
                ColumnConfig.newBuilder().setName("exception").setPattern("%ex{full}").build()
        };

        // filter for the appender to keep only errors
        // ThresholdFilter filter = ThresholdFilter.createFilter(Level.INFO, null,
        // null);

        // The creation of the new database appender passing:
        // - the name of the appender
        // - ignore exceptions encountered when appending events are logged
        // - the filter created previously
        // - the connectionSource,
        // - log buffer size,
        // - the name of the table
        // - the config of the columns.
        JdbcAppender appender = JdbcAppender.newBuilder()
                .setName("DB")
                .setIgnoreExceptions(true)
                // .setFilter(filter)
                .setConnectionSource(connectionSource)
                .setBufferSize(1)
                .setTableName("LOGS")
                .setColumnConfigs(columnConfigs)
                .setColumnMappings(new ColumnMapping[] {})
                .build();

        appender.start();
        config.getRootLogger().addAppender(appender, null, null);

    }
}