package com.jp;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.jp.api.Welcome;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ApiApplicationTests {

	@Autowired
	Welcome welcome;

	@Autowired
	DataSource dataSource;

	@Test
	public void contextLoads() throws SQLException {

		Connection con = dataSource.getConnection();

		System.out.println("Connection : " + con);

		assertNotNull(welcome);
	}
}
