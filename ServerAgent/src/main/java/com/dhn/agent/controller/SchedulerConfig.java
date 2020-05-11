package com.dhn.agent.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
public class SchedulerConfig implements SchedulingConfigurer{

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		ThreadPoolTaskScheduler tpts = new ThreadPoolTaskScheduler();
		
		tpts.setPoolSize(20);
		tpts.setThreadNamePrefix("DHN Scheduler Task Pool - ");
		tpts.initialize();
		
		taskRegistrar.setTaskScheduler(tpts);
	}

}
