package com.jp;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class ApiApplication {

	@Value("${server.port}")
	private int serverPort;

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);

		log.debug("This is a debug message 1111111111");
		log.info("This is an info message");
		log.warn("This is a warn message");
		log.error("This is an error message");
		log.debug("null");
	}

	@PostConstruct()
	public void started() throws IOException {
		System.out.println("Application started at port : " + serverPort);
	}

	@PreDestroy
	public void onExit() {
		System.out.println("Exiting app...");
	}

}
