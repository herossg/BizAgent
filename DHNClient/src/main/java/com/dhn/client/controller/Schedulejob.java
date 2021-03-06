package com.dhn.client.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Schedulejob {
	
	private static final Logger log = LoggerFactory.getLogger(Schedulejob.class);

	//Log log = new Log
	@Scheduled(cron = "*/2 * * * * *")
	public void sendRequest() {
		//log.info("...");
		if(DhnController.isStart)
			SendRequest.run();
	}
	
	@Scheduled(fixedRate = 5000)
	public void login() {
		//log.info("Log in 시도");
		if(!DhnController.isStart)
			SendRequest.login();
	}
}
