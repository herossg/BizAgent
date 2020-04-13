package com.dhn.agent.controller;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.dhn.agent.model.DhnRequest;
import com.dhn.agent.model.DhnResult;
import com.dhn.agent.model.DhnUser;
import com.dhn.agent.model.JsonResult;
import com.dhn.agent.service.DhnRequestService;
import com.dhn.agent.service.DhnResultService;
import com.dhn.agent.service.DhnUserService;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@RestController
public class DhnController {
	@Autowired
	private DhnRequestService dhnReqService;
	
	@Autowired
	private DhnResultService dhnResService;
	
	@Autowired
	DhnUserService dhnUserService;
	
	private @Value("${PROFILE_KEY}") String PROFILE_KEY;
	private @Value("${CENTER_SERVER}") String CENTER_SERVER;
	private ObjectMapper mapper = new ObjectMapper();
	private static final Logger log = LoggerFactory.getLogger(DhnController.class);
	private Object sync1 = new Object();
	
	@RequestMapping(value = "/req",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<DhnRequest>> getAllRequest() {
		List<DhnRequest> dhnReqs = dhnReqService.findAll();
        
		return new ResponseEntity<List<DhnRequest>>(dhnReqs, /*headers,*/ HttpStatus.OK);
	}
	
	@RequestMapping(value = "/req/{id}",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<DhnRequest> getRequest(@PathVariable("msgid") Integer id) {
		DhnRequest req = dhnReqService.findById(id);
		return new ResponseEntity<DhnRequest>(req, HttpStatus.OK);
	}
	
	@PostMapping(value="/req")
	public ResponseEntity<List<String>> save(@RequestHeader(name="userid", required=true) String userid,
			                           @RequestBody List<DhnRequest> dhnRequest) {
		
		List<String> msgids = new ArrayList<String>();
		dhnRequest.forEach(dr -> {
			dr.setUserid(userid);
			msgids.add(dr.getMSGID());
		});
		
		dhnReqService.save(dhnRequest);
		
		return new ResponseEntity<List<String>>(msgids, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/res/{msgid}",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<JsonResult> getResult(@PathVariable("msgid") String msgid) {
		DhnResult res = dhnResService.selectByMsgidQuery(msgid);
		JsonResult json = new JsonResult();
		if(res != null) {
			json.setCode(res.getCode());
			json.setMessage(res.getMessage());
		} else {
			json.setCode("9999");
			json.setMessage("존재하지 않음");
		}
		return new ResponseEntity<JsonResult>(json, HttpStatus.OK);
	}
	
	@PostMapping(value="/resall")
	public ResponseEntity<List<DhnResult>> save(@RequestHeader(name="userid", required=true) String userid) {
		//log.info("Result 호출 됨");
		//synchronized (sync1) {
			//log.info("Result 시작 됨");
			SimpleDateFormat format1 = new SimpleDateFormat ( "yyyyMMddHHmmss");
			Date time = new Date();
			String send_group = format1.format(time);
			dhnResService.updateSendGroupByUseridSyncQuery(send_group, userid);
			List<DhnResult> dhnResult = dhnResService.selectByUseridSendgroupQuery(userid, send_group);
			dhnResService.updateByMsgidSyncQuery(userid, send_group);
			
			if(dhnResult.size()> 0)
				log.info("Result ( " + send_group + " ) : " + userid + " - " + dhnResult.size() + " 건 전송");
			
			return new ResponseEntity<List<DhnResult>>(dhnResult, HttpStatus.OK);
		//}
	}
	
	@GetMapping(value="/", produces = {MediaType.APPLICATION_JSON_VALUE})
	public String root() {
		return CENTER_SERVER;
	}

	@GetMapping(value="/sender/token", produces = {MediaType.APPLICATION_JSON_VALUE})
	public String sender_token( 
		@RequestParam(value="yellowId")String yellowId, 
		@RequestParam(value="phoneNumber")String phoneNumber) {

		String URL = CENTER_SERVER + "api/v1/" + PROFILE_KEY + "/sender/token?yellowId=" +yellowId + "&phoneNumber=" +phoneNumber ;
		ResponseEntity<String> result = reqGet(URL);
		return result.getBody();
	}

	@PostMapping(value="/sender/create", produces = {MediaType.APPLICATION_JSON_VALUE})
	public String sender_create(
			@RequestHeader(name="account", required = false) String account,
			@RequestHeader(name="token", required = true) String token,
			@RequestHeader(name="phoneNumber", required = true) String phoneNumber,
			@RequestBody Map<String, String> postParam
			) {

		String URL = CENTER_SERVER + "api/v3/" + PROFILE_KEY + "/sender/create";
		
		Map<String, String> header = new HashMap<>();
		if(account != null && !account.isEmpty()) {
			header.put("account", account);
		} else {
			header.put("account", "ceodhn");
		}
		header.put("token", token);
		header.put("phoneNumber", phoneNumber);
		
		ResponseEntity<String> result = reqPost(URL, header, postParam);
		log.info(result.toString());
		log.info(result.getBody());
		return result.getBody();
	}
	
	@GetMapping(value="/sender", produces = {MediaType.APPLICATION_JSON_VALUE})
	public String sender( 
		@RequestParam(value="senderKey")String senderKey) {

		String URL = CENTER_SERVER + "api/v3/" + PROFILE_KEY + "/sender?senderKey=" +senderKey;
		ResponseEntity<String> result = reqGet(URL);
		return result.getBody();
	}
	
	@PostMapping(value="/sender/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
	public String sender_delete(
			@RequestHeader(name="account", required = false) String account,
			@RequestBody Map<String, String> postParam
			) {

		String URL = CENTER_SERVER + "api/v1/" + PROFILE_KEY + "/sender/delete";
		
		Map<String, String> header = new HashMap<>();
		if(account != null && !account.isEmpty()) {
			header.put("account", account);
		} else {
			header.put("account", "ceodhn");
		}
		
		ResponseEntity<String> result = reqPost(URL, header, postParam);
		
		return result.getBody();
	}
	
	@PostMapping(value="/sender/recover", produces = {MediaType.APPLICATION_JSON_VALUE})
	public String sender_recover(
			@RequestHeader(name="account", required = false) String account,
			@RequestBody Map<String, String> postParam
			) {

		String URL = CENTER_SERVER + "api/v1/" + PROFILE_KEY + "/sender/recover";
		
		Map<String, String> header = new HashMap<>();
		if(account != null && !account.isEmpty()) {
			header.put("account", account);
		} else {
			header.put("account", "ceodhn");
		}
		
		ResponseEntity<String> result = reqPost(URL, header, postParam);
		
		return result.getBody();
	}	
	
	@GetMapping(value="/category/all", produces = {MediaType.APPLICATION_JSON_VALUE})
	public String category_all() {
		String URL = CENTER_SERVER + "api/v1/" + PROFILE_KEY + "/category/all";
		ResponseEntity<String> result = reqGet(URL);
		
		return result.getBody();
		
	}
	
	@GetMapping(value="/category", produces = {MediaType.APPLICATION_JSON_VALUE})
	public String category( 
		@RequestParam(value="categoryCode")String categoryCode) {

		String URL = CENTER_SERVER + "api/v1/" + PROFILE_KEY + "/category?categoryCode=" +categoryCode;
		ResponseEntity<String> result = reqGet(URL);
		Map<String, Object> res;
		try {
			res = mapper.readValue(result.getBody(),  new TypeReference<Map<String, Object>>(){});
			return mapper.writeValueAsString(res.get("data"));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.toString();
		}
	}	

	@PostMapping(value="/template/create", produces = {MediaType.APPLICATION_JSON_VALUE})
	public String template_create(
			@RequestHeader(name="account", required = false) String account,
			@RequestBody Map<String, String> postParam
			) {

		String URL = CENTER_SERVER + "api/v1/" + PROFILE_KEY + "/template/create";
		
		Map<String, String> header = new HashMap<>();
		if(account != null && !account.isEmpty()) {
			header.put("account", account);
		} else {
			header.put("account", "ceodhn");
		}
		
		ResponseEntity<String> result = reqPost(URL, header, postParam);
		
		return result.getBody();
	}	
	
	@GetMapping(value="/template", produces = {MediaType.APPLICATION_JSON_VALUE})
	public String template( 
		@RequestParam(value="senderKey")String senderKey,
		@RequestParam(value="templateCode")String templateCode,
		@RequestParam(value="senderKeyType", required = false) String senderKeyType
			) {

		String URL = CENTER_SERVER + "api/v1/" + PROFILE_KEY + "/template?senderKey=" +senderKey + "&templateCode=" + templateCode;
		if(senderKeyType != null && !senderKeyType.isEmpty()) {
			URL = URL + "&senderKeyType=" + senderKeyType;
		}
		
		ResponseEntity<String> result = reqGet(URL);
		
		return result.getBody();
	}		
	
	@PostMapping(value="/template/request", produces = {MediaType.APPLICATION_JSON_VALUE})
	public String template_request(
			@RequestHeader(name="account", required = false) String account,
			@RequestBody Map<String, String> postParam
			) {

		String URL = CENTER_SERVER + "api/v1/" + PROFILE_KEY + "/template/request";
		
		Map<String, String> header = new HashMap<>();
		if(account != null && !account.isEmpty()) {
			header.put("account", account);
		} else {
			header.put("account", "ceodhn");
		}
		
		ResponseEntity<String> result = reqPost(URL, header, postParam);
		
		return result.getBody();
	}	
	
	@PostMapping(value="/template/update", produces = {MediaType.APPLICATION_JSON_VALUE})
	public String template_update(
			@RequestHeader(name="account", required = false) String account,
			@RequestBody Map<String, String> postParam
			) {

		String URL = CENTER_SERVER + "api/v1/" + PROFILE_KEY + "/template/update";
		
		Map<String, String> header = new HashMap<>();
		if(account != null && !account.isEmpty()) {
			header.put("account", account);
		} else {
			header.put("account", "ceodhn");
		}
		
		ResponseEntity<String> result = reqPost(URL, header, postParam);
		
		return result.getBody();
	}	
	
	@PostMapping(value="/template/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
	public String template_delete(
			@RequestHeader(name="account", required = false) String account,
			@RequestBody Map<String, String> postParam
			) {

		String URL = CENTER_SERVER + "api/v1/" + PROFILE_KEY + "/template/delete";
		
		Map<String, String> header = new HashMap<>();
		if(account != null && !account.isEmpty()) {
			header.put("account", account);
		} else {
			header.put("account", "ceodhn");
		}
		
		ResponseEntity<String> result = reqPost(URL, header, postParam);
		
		return result.getBody();
	}	
	
	@GetMapping(value="/template/last_modified", produces = {MediaType.APPLICATION_JSON_VALUE})
	public String template_last_modified( 
		@RequestParam(value="senderKey", required = false)String senderKey,
		@RequestParam(value="senderKeyType", required = false)String senderKeyType,
		@RequestParam(value="since", required = false)String since,
		@RequestParam(value="page", required = false)int page,
		@RequestParam(value="count", required = false)int count
		) {
		String connStr = "?";
		String URL = CENTER_SERVER + "api/v1/" + PROFILE_KEY + "/template/last_modified";
		
		if(senderKey != null && !senderKey.isEmpty()) {
			URL = URL + connStr + "senderKey=" + senderKey;
			connStr = "&";
		}
		
		if(senderKeyType != null && !senderKeyType.isEmpty()) {
			URL = URL + connStr + "senderKeyType=" + senderKeyType;
			connStr = "&";
		}		
		
		if(since != null && !since.isEmpty()) {
			URL = URL + connStr + "since=" + since;
			connStr = "&";
		}		
		
		if(page == 0 ) {
			page = 1;
		}
		
		URL = URL + connStr + "page=" + page;
		connStr = "&";
				
		
		if(count == 0 ) {
			count = 1000;
		}
		
		URL = URL + connStr + "count=" + count;
				
		ResponseEntity<String> result = reqGet(URL);
		
		return result.getBody();
	}		
		
	@PostMapping(value="/template/comment", produces = {MediaType.APPLICATION_JSON_VALUE})
	public String template_comment(
			@RequestHeader(name="account", required = false) String account,
			@RequestBody Map<String, String> postParam
			) {

		String URL = CENTER_SERVER + "api/v1/" + PROFILE_KEY + "/template/comment";
		
		Map<String, String> header = new HashMap<>();
		if(account != null && !account.isEmpty()) {
			header.put("account", account);
		} else {
			header.put("account", "ceodhn");
		}
		
		ResponseEntity<String> result = reqPost(URL, header, postParam);
		
		return result.getBody();
	}	
		
	@PostMapping(value="/template/comment_file", produces = {MediaType.APPLICATION_JSON_VALUE})
	public String template_comment_file(
			@RequestHeader(name="account", required = false) String account,
			@RequestBody Map<String, String> postParam
			) {

		String URL = CENTER_SERVER + "api/v1/" + PROFILE_KEY + "/template/comment_file";
		
		Map<String, String> header = new HashMap<>();
		if(account != null && !account.isEmpty()) {
			header.put("account", account);
		} else {
			header.put("account", "ceodhn");
		}
		
		ResponseEntity<String> result = reqPost(URL, header, postParam);
		
		return result.getBody();
	}		
	
	@PostMapping(value="/template/test_approve", produces = {MediaType.APPLICATION_JSON_VALUE})
	public String template_test_approve(
			@RequestHeader(name="account", required = false) String account,
			@RequestBody Map<String, String> postParam
			) {

		String URL = CENTER_SERVER + "api/v1/" + PROFILE_KEY + "/template/test_approve";
		
		Map<String, String> header = new HashMap<>();
		if(account != null && !account.isEmpty()) {
			header.put("account", account);
		} else {
			header.put("account", "ceodhn");
		}
		
		ResponseEntity<String> result = reqPost(URL, header, postParam);
		
		return result.getBody();
	}	
	
	@PostMapping(value="/template/test_reject", produces = {MediaType.APPLICATION_JSON_VALUE})
	public String template_test_reject(
			@RequestHeader(name="account", required = false) String account,
			@RequestBody Map<String, String> postParam
			) {

		String URL = CENTER_SERVER + "api/v1/" + PROFILE_KEY + "/template/test_reject";
		
		Map<String, String> header = new HashMap<>();
		if(account != null && !account.isEmpty()) {
			header.put("account", account);
		} else {
			header.put("account", "ceodhn");
		}
		
		ResponseEntity<String> result = reqPost(URL, header, postParam);
		
		return result.getBody();
	}	
		
	@GetMapping(value="/group", produces = {MediaType.APPLICATION_JSON_VALUE})
	public String group() {

		String URL = CENTER_SERVER + "api/v1/" + PROFILE_KEY + "/group";
		
		ResponseEntity<String> result = reqGet(URL);
		
		return result.getBody();
	}		
	
	@GetMapping(value="/group/sender", produces = {MediaType.APPLICATION_JSON_VALUE})
	public String group_sender( 
		@RequestParam(value="groupKey")String groupKey) {

		String URL = CENTER_SERVER + "api/v3/" + PROFILE_KEY + "/group/sender?groupKey=" +groupKey;
		
		ResponseEntity<String> result = reqGet(URL);
		
		return result.getBody();
	}	
	
	@PostMapping(value="/group/sender/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	public String group_sender_add(
			@RequestHeader(name="account", required = false) String account,
			@RequestBody Map<String, String> postParam
			) {

		String URL = CENTER_SERVER + "api/v1/" + PROFILE_KEY + "/group/sender/add";
		
		Map<String, String> header = new HashMap<>();
		if(account != null && !account.isEmpty()) {
			header.put("account", account);
		} else {
			header.put("account", "ceodhn");
		}
		
		ResponseEntity<String> result = reqPost(URL, header, postParam);
		
		return result.getBody();
	}	
		
	@PostMapping(value="/group/sender/remove", produces = {MediaType.APPLICATION_JSON_VALUE})
	public String group_sender_remove(
			@RequestHeader(name="account", required = false) String account,
			@RequestBody Map<String, String> postParam
			) {

		String URL = CENTER_SERVER + "api/v1/" + PROFILE_KEY + "/group/sender/remove";
		
		Map<String, String> header = new HashMap<>();
		if(account != null && !account.isEmpty()) {
			header.put("account", account);
		} else {
			header.put("account", "ceodhn");
		}
		
		ResponseEntity<String> result = reqPost(URL, header, postParam);
		
		return result.getBody();
	}	
	
	@PostMapping(value="/channel/create", produces = {MediaType.APPLICATION_JSON_VALUE})
	public String channel_create(
			@RequestHeader(name="account", required = false) String account,
			@RequestBody Map<String, String> postParam
			) {

		String URL = CENTER_SERVER + "api/v1/" + PROFILE_KEY + "/group/sender/remove";
		
		Map<String, String> header = new HashMap<>();
		if(account != null && !account.isEmpty()) {
			header.put("account", account);
		} else {
			header.put("account", "ceodhn");
		}
		
		ResponseEntity<String> result = reqPost(URL, header, postParam);
		
		return result.getBody();
	}	
	
	@PostMapping(value="/channel", produces = {MediaType.APPLICATION_JSON_VALUE})
	public String channel(
			@RequestHeader(name="account", required = false) String account,
			@RequestBody Map<String, String> postParam
			) {

		String URL = CENTER_SERVER + "api/v2/" + PROFILE_KEY + "/channel";
		
		Map<String, String> header = new HashMap<>();
		if(account != null && !account.isEmpty()) {
			header.put("account", account);
		} else {
			header.put("account", "ceodhn");
		}
		
		ResponseEntity<String> result = reqPost(URL, header, postParam);
		
		return result.getBody();
	}

	@PostMapping(value="/channel/all", produces = {MediaType.APPLICATION_JSON_VALUE})
	public String channel_all(
			@RequestHeader(name="account", required = false) String account,
			@RequestBody Map<String, String> postParam
			) {

		String URL = CENTER_SERVER + "api/v2/" + PROFILE_KEY + "/channel/all";
		
		Map<String, String> header = new HashMap<>();
		if(account != null && !account.isEmpty()) {
			header.put("account", account);
		} else {
			header.put("account", "ceodhn");
		}
		
		ResponseEntity<String> result = reqPost(URL, header, postParam);
		
		return result.getBody();
	}	
	
	@PostMapping(value="/channel/update", produces = {MediaType.APPLICATION_JSON_VALUE})
	public String channel_update(
			@RequestHeader(name="account", required = false) String account,
			@RequestBody Map<String, String> postParam
			) {

		String URL = CENTER_SERVER + "api/v2/" + PROFILE_KEY + "/channel/update";
		
		Map<String, String> header = new HashMap<>();
		if(account != null && !account.isEmpty()) {
			header.put("account", account);
		} else {
			header.put("account", "ceodhn");
		}
		
		ResponseEntity<String> result = reqPost(URL, header, postParam);
		
		return result.getBody();
	}

	@PostMapping(value="/channel/senders", produces = {MediaType.APPLICATION_JSON_VALUE})
	public String channel_senders(
			@RequestHeader(name="account", required = false) String account,
			@RequestBody Map<String, String> postParam
			) {

		String URL = CENTER_SERVER + "api/v2/" + PROFILE_KEY + "/channel/senders";
		
		Map<String, String> header = new HashMap<>();
		if(account != null && !account.isEmpty()) {
			header.put("account", account);
		} else {
			header.put("account", "ceodhn");
		}
		
		ResponseEntity<String> result = reqPost(URL, header, postParam);
		
		return result.getBody();
	}
	
	@PostMapping(value="/channel/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
	public String channel_delete(
			@RequestHeader(name="account", required = false) String account,
			@RequestBody Map<String, String> postParam
			) {

		String URL = CENTER_SERVER + "api/v2/" + PROFILE_KEY + "/channel/delete";
		
		Map<String, String> header = new HashMap<>();
		if(account != null && !account.isEmpty()) {
			header.put("account", account);
		} else {
			header.put("account", "ceodhn");
		}
		
		ResponseEntity<String> result = reqPost(URL, header, postParam);
		
		return result.getBody();
	}
	
	@PostMapping(value="/ft/image", produces = {MediaType.APPLICATION_JSON_VALUE})
	public String ft_image(
			@RequestHeader(name="account", required = false) String account,
			@RequestParam("image") MultipartFile image
			) {

		String URL = CENTER_SERVER + "api/v2/" + PROFILE_KEY + "/ft/upload_image";
		
		Map<String, String> header = new HashMap<>();
		if(account != null && !account.isEmpty()) {
			header.put("account", account);
		} else {
			header.put("account", "ceodhn");
		}
		
		log.info("File Name : " + image.getOriginalFilename());
		ResponseEntity<String> result = uploadAgreement(URL, header, image);
		return result.getBody();
	}
	
	@GetMapping(value="/cstop", produces = {MediaType.APPLICATION_JSON_VALUE})
	public String clientstop() {
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
					tlist = tlist + " ID : " +  t.getId() + " , "  + " Name : " + t.getName() + " 종료 요청 했습니다.\r";
					t.interrupt();
				}
			}
		}
		
		return tlist;
		
	}
	
	@GetMapping(value="/cstatus", produces = {MediaType.APPLICATION_JSON_VALUE})
	public String clientstatus() {
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
					tlist = tlist + " ID : " +  t.getId() + " , "  + " Name : " + t.getName() + "\r";
				}
			}
		}
		
		return tlist;
		
	}

	@GetMapping(value="/cstart", produces = {MediaType.APPLICATION_JSON_VALUE})
	public String clientsStart() {
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
		int cnt = 0;
		
		for (Thread t : threads) {
			if(t != null) {
				
				if(t.getName().indexOf("UserResultSend") >= 0) {
					t.interrupt();
					tlist = tlist + " ID : " +  t.getId() + " , "  + " Name : " + t.getName() + "\r";
					cnt ++;
				}
			}
		}
		
		if(cnt > 0) {
			return "실행중인 Client Thread 가 존재합니다.\r잠시후 다시 시도 하세요.";
		} else {
			List<DhnUser> dhnUsers = dhnUserService.findAll();
			dhnUsers.forEach(e -> {
				UserResultSend urs = new UserResultSend();
				urs.dhnResService = dhnResService;
				urs.clientIp = e.getIp();
				urs.userId = e.getUserid();
				
				Thread t = new Thread(urs);
				t.setName("UserResultSend_" + urs.userId);
				t.start();
			});
			for(DhnUser d : dhnUsers) {
				tlist = tlist + "UserResultSend_" + d.getUserid() + " 정상 실행 요청 되었습니다. \r";
			}
		}
		
		return tlist;
		
	}
	
	private ResponseEntity<String> reqPost(String url, Map<String, String> header, Map<String, String> param) {
		HttpHeaders headers = new HttpHeaders();
		final String URL = url;
		List<MediaType> accept = new ArrayList<>();
		accept.add(MediaType.APPLICATION_JSON);
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(accept);
		
		for( String key : header.keySet() ){
			headers.add(key,header.get(key));
		}
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);

		String jsonStr;
		try {
			jsonStr = mapper.writeValueAsString(param);
			HttpEntity<String> entity = new HttpEntity<String>(jsonStr,headers);
			
			RestTemplate restTemp = new RestTemplate();
			ResponseEntity<String> response = restTemp.postForEntity(URL, entity, String.class);
			return response;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	private ResponseEntity<String> reqGet(String url) {
		final String URL = url;
				
		RestTemplate restTemp = new RestTemplate();
		
		ResponseEntity<String> response = restTemp.getForEntity(URL, String.class); 
		log.info("Get Response : " + response.getBody());
		return response;
		
	}

	public ResponseEntity<String> uploadAgreement(String url, Map<String, String> header, MultipartFile image) {

		MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();

	    bodyMap.add("image", generateFilenameAwareByteArrayResource(image));

	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		
	    for( String key : header.keySet() ){
			headers.add(key,header.get(key));
		}
		
	    HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyMap, headers);

	    RestTemplate restTemp = new RestTemplate();
	    
	    ResponseEntity<String> response = restTemp.postForEntity(url, requestEntity, String.class);
	    log.debug("Response: {}", response);
	    log.debug("Response body: {}", response.getBody());

	    return response;
	}
	
	private FilenameAwareInputStreamResource generateFilenameAwareByteArrayResource(MultipartFile agreement) {
	    try {
	        return new FilenameAwareInputStreamResource(agreement.getInputStream(), agreement.getSize(), agreement.getOriginalFilename());
	    } catch (Exception e) {
	        log.error("Occur exception", e);
	    } 
	    return null;
	}

	public static class FilenameAwareInputStreamResource extends InputStreamResource {
	    private final String filename;
	    private final long contentLength;

	    public FilenameAwareInputStreamResource(InputStream inputStream, long contentLength, String filename) {
	        super(inputStream);
	        this.filename = filename;
	        this.contentLength = contentLength;
	    }

	    @Override
	    public String getFilename() {
	        return filename;
	    }

	    @Override
	    public long contentLength() {
	        return contentLength;
	    }
	}
}
