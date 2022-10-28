package com.jp.config;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// @Component
public class JdbcConnection {

    private BasicDataSource dataSource;

    // @Autowired
    // DataSource myDataSource;

    private JdbcConnection() {
    }

    public Connection getConnection() throws SQLException {
        if (dataSource == null) {
            try {
                dataSource = new BasicDataSource();
                dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
                dataSource.setUrl("jdbc:mysql://localhost:3306/qrcode");
                dataSource.setUsername("temp");
                dataSource.setPassword("temp");
            } catch (Exception e) {
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                e.printStackTrace();

            }

        }
        return dataSource.getConnection();
    }
    // public Connection getConnection() {

    // try {
    // return myDataSource.getConnection();
    // } catch (SQLException e) {
    // // TODO Auto-generated catch block
    // System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

    // e.printStackTrace();
    // }

    // return null;

    // }
}