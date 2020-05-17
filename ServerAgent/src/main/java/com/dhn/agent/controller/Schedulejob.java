package com.dhn.agent.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Schedulejob {
	
	@Autowired
	SendRequest sendRequest;
	
	@Autowired
	SendMMS sendMMS;
	
	@Autowired
	ResultSMSLog resultSMSLog;

	@Autowired
	ResultMMSLog resultMMSLog;

	private static final Logger log = LoggerFactory.getLogger(Schedulejob.class);

	//Log log = new Log
	@Scheduled(fixedRate = 5000)
	@Async
	public void sendRequest() {
		//log.info("스케줄러 실행 됨.");
		sendRequest.run();
	}

	//Log log = new Log
	@Scheduled(fixedRate = 5000)
	@Async
	public void sendMMS() {
		//log.info("스케줄러 실행 됨.");
		sendMMS.run();
	}

	//Log log = new Log
	@Scheduled(fixedRate = 5000)
	@Async
	public void ResultSMS() {
		//log.info("SMS Log 처리 실행 됨.");
		resultSMSLog.run();
	}

	//Log log = new Log
	@Scheduled(fixedRate = 5000)
	@Async
	public void ResultMMS() {
		//log.info("SMS Log 처리 실행 됨.");
		resultMMSLog.run();
	}
	
}
