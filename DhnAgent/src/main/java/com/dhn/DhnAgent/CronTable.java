package com.dhn.DhnAgent;

import java.sql.Date;
import java.util.List;

import javax.print.attribute.standard.DateTimeAtCompleted;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.dhn.DhnAgent.Price.Price;

@Service
public class CronTable {
	
	@Autowired
	PriceDao priceDao;
	
	@Scheduled(initialDelay=1000, fixedDelay = 5000)
	public void otherJob() {
		//List<Price> list = priceDao.getMemberPrice(3);
		//PriceDao priceDao = new PriceDao();
				
		List<Price> list = priceDao.getMemberPrice(3);
		
		System.out.println("일하자 : " + list.size());
		
	}
}
