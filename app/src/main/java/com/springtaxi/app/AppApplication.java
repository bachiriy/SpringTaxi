package com.springtaxi.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class AppApplication {

	// Create a logger instance
	private static final Logger logger = LoggerFactory.getLogger(AppApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(AppApplication.class, args);
	}
}
