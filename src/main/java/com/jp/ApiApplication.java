package com.jp;

import java.io.IOException;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jp.model.User;
import com.jp.model.repo.UserRepo;
import com.jp.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class ApiApplication {
	@Autowired
	UserService userService;

	@Value("${server.port}")
	private int serverPort;

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
		log.warn("** APPLICATION STARTED. **");
	}

	@PostConstruct()
	public void onStart() throws IOException {
		initCheck();
		System.out.println("Application started at port : " + serverPort);
	}

	@PreDestroy
	public void onExit() {
		System.out.println("Exiting app...");
	}

	private void initCheck() {
		userService.initAdminUserCheck();
	}

}
