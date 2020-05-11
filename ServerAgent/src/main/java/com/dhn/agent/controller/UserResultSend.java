package com.dhn.agent.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.dhn.agent.model.DhnResult;
import com.dhn.agent.service.DhnResultService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class UserResultSend implements Runnable {
	
	public DhnResultService dhnResService;


	private static final Logger log = LoggerFactory.getLogger(SendRequest.class);
	public String clientIp;
	public String userId;
	public String port;
	public int errcnt = 1;

	@Override
	public void run() {
		List<DhnResult> dhnResults;
		while(!Thread.currentThread().isInterrupted()) {
			dhnResults = dhnResService.selectByUserid(userId);

			if(dhnResults.size() > 0) {
				dhnResults.forEach(e -> { 
					e.setSync("Y");	
				});
				
				final String URL = "http://" + clientIp + ":" + port + "/results";
				
				try {
					HttpHeaders headers = new HttpHeaders();
					List<MediaType> accept = new ArrayList<>();
					accept.add(MediaType.APPLICATION_JSON);
					
					headers.setContentType(MediaType.APPLICATION_JSON);
					headers.setAccept(accept);
					
					ObjectMapper mapper = new ObjectMapper();
					//mapper.setSerializationInclusion(Include.NON_NULL);
					
					RestTemplate restTemp = new RestTemplate();
					String jsonStr = mapper.writeValueAsString(dhnResults);
					
					HttpEntity<String> entity = new HttpEntity<String>(jsonStr, headers);
					//log.info(jsonStr);
					ResponseEntity<String> response = restTemp.postForEntity(URL, entity, String.class);
					
					dhnResService.SaveAll(dhnResults);
					log.info("Client ID : " + userId + " -> " + dhnResults.size() + " 건 전송 성공");
					errcnt = 1;
				} catch(Exception ex) {
					log.info("Client : " + URL + " ( Error CNT : " + errcnt +  " ) -> " + ex.getMessage());
					errcnt++;
					try {
						Thread.sleep(60000 * errcnt);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						//e1.printStackTrace();
						Thread.currentThread().interrupt();
					}
				}
				dhnResults.clear();
			}
		}
	}
}
