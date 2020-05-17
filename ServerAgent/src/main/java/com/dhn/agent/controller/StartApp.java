package com.dhn.agent.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;

import com.dhn.agent.model.DhnUser;
import com.dhn.agent.service.DhnResultService;
import com.dhn.agent.service.DhnUserService;

@Configuration
public class StartApp implements CommandLineRunner, DisposableBean, ApplicationListener<ContextClosedEvent> {

	@Autowired
	DhnUserService dhnUserService;
	
	@Autowired
	DhnResultService dhnResService;
	
	private static final Logger log = LoggerFactory.getLogger(SendRequest.class);
	
	@Override
	public void run(String... args) throws Exception {
		
		List<DhnUser> dhnUsers = dhnUserService.findAll();
		dhnUsers.forEach(e -> {
			if(e.getUseflag().toUpperCase().equals("Y")) {
				UserResultSend urs = new UserResultSend();
				urs.dhnResService = dhnResService;
				urs.clientIp = e.getIp();
				urs.userId = e.getUserid();
				urs.port = e.getPort();
				
				Thread t = new Thread(urs);
				t.setName("UserResultSend_" + urs.userId);
				t.start();
			}
		});
	}
	
	@Override
	public void destroy() throws Exception {
		UserSendClose();
    }

	@Override
	public void onApplicationEvent(ContextClosedEvent event) {
		log.info("종료 이벤트 처리 시작.");
		UserSendClose();
		log.info("종료 이벤트 처리 끝.");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	
	public void UserSendClose() {
		ThreadGroup rootGroup = Thread.currentThread().getThreadGroup();
		ThreadGroup parentGroup;
		while ((parentGroup = rootGroup.getParent()) != null) {
		    rootGroup = parentGroup;
		}
		 
		Thread[] threads = new Thread[rootGroup.activeCount()];
		while (rootGroup.enumerate(threads, true) == threads.length) {
		    threads = new Thread[threads.length * 2];
		}
		 
		String tlist = "";
		
		for (Thread t : threads) {
			if(t != null) {
				
				if(t.getName().indexOf("UserResultSend") >= 0) {
					log.info(" ID : " +  t.getId() + " , "  + " Name : " + t.getName() + " 종료 요청 했습니다.");
					t.interrupt();
				}
			}
		}
	}
}
