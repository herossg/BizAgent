package com.dhn.agent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ServerAgentApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerAgentApplication.class, args);
	}

}
