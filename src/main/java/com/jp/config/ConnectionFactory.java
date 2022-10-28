package com.jp.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnection;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class ConnectionFactory {
    private static interface Singleton {
        final ConnectionFactory INSTANCE = new ConnectionFactory();
    }

    private final DataSource dataSource;

    @Value("${spring.datasource.url}")
    private static String url_base;

    public ConnectionFactory() {
        String url = "jdbc:mysql://localhost:3306/qrcode";
        Properties properties = new Properties();
        properties.setProperty("user", "temp");
        properties.setProperty("password", "temp"); // or get properties from some configuration file

        GenericObjectPool<PoolableConnection> pool = new GenericObjectPool<PoolableConnection>();
        DriverManagerConnectionFactory connectionFactory = new DriverManagerConnectionFactory(
                url, properties);
        new PoolableConnectionFactory(
                connectionFactory, pool, null, "SELECT 1", 3, false, false, Connection.TRANSACTION_READ_COMMITTED);

        this.dataSource = new PoolingDataSource(pool);
    }

    public static Connection getConnection() throws SQLException {
        System.out.println("Base URL : " + url_base);
        return Singleton.INSTANCE.dataSource.getConnection();
    }
}
