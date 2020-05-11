package com.dhn.agent.controller;

import java.util.List;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.dhn.agent.model.DhnUser;
import com.dhn.agent.service.DhnResultService;
import com.dhn.agent.service.DhnUserService;

@Configuration
public class StartApp implements CommandLineRunner, DisposableBean {

	@Autowired
	DhnUserService dhnUserService;
	
	@Autowired
	DhnResultService dhnResService;
	
	@Override
	public void run(String... args) throws Exception {
		
		List<DhnUser> dhnUsers = dhnUserService.findAll();
		dhnUsers.forEach(e -> {
			UserResultSend urs = new UserResultSend();
			urs.dhnResService = dhnResService;
			urs.clientIp = e.getIp();
			urs.userId = e.getUserid();
			urs.port = e.getPort();
			
			Thread t = new Thread(urs);
			t.setName("UserResultSend_" + urs.userId);
			t.start();
		});
	}
	
	@Override
	public void destroy() throws Exception {
		ThreadGroup rootGroup = Thread.currentThread().getThreadGroup();
		ThreadGroup parentGroup;
		while ((parentGroup = rootGroup.getParent()) != null) {
		    rootGroup = parentGroup;
		}
		 
		Thread[] threads = new Thread[rootGroup.activeCount()];
		while (rootGroup.enumerate(threads, true) == threads.length) {
		    threads = new Thread[threads.length * 2];
		}
		 
		for (Thread t : threads) {
			if(t != null) {
				
				if(t.getName().indexOf("UserResultSend") >= 0) {
					t.interrupt();
				}
			}
		}
		
    } 
}
