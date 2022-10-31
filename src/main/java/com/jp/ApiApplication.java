package com.jp;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jp.security.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class ApiApplication {
	@Autowired
	UserService userService;

	@Value("${server.port}")
	private int serverPort;

	@Value("${app.version}")
	public String version;

	public static String appVersion;

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
		log.warn("** APPLICATION STARTED. **");
	}

	@PostConstruct()
	public void onStart() throws Exception {
		initCheck();
		ApiApplication.appVersion = version;
		System.out.println("Application started at port : " + serverPort);
	}

	@PreDestroy
	public void onExit() {
		System.out.println("Exiting app...");
	}

	private void initCheck() throws Exception {
		userService.initAdminUserCheck();
	}

}
