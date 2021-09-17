package com.eureka.eurekaservice;

import com.eureka.eurekaservice.db.MyStorage;
import com.eureka.eurekaservice.dto.Record;
import com.eureka.eurekaservice.dto.*;
import com.eureka.eurekaservice.entity.SccoRecord;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1")
@Slf4j
public class ExternalController {

	static final String recordUri = "https://api.stringee.com/v1/stringeecall2/recording";

	static final String tokenUri = "https://demo-deploy-sv.herokuapp.com/api/v1/video_call/token";


	@PostMapping(path = "/video_call/token")
	@CrossOrigin
	public ResponseEntity<ApiTokenResponse> getToken(@RequestBody ApiTokenRequest request) {
		String token = TokenUtil.genAccessToken(request.getUserId(),
				"SKnisBolJVGOhRoa61exg48aNhA6N5qpRb",
				"MGc4N3dWSWg1Rzd6YXdqMGx3c1ZTRmI2TXRNUWZZ",
				3600);
		return new ResponseEntity<>(ApiTokenResponse.builder().token(token).build(), HttpStatus.OK);
	}

	@GetMapping(path = "/video_call/recording")
	public ResponseEntity record(@RequestBody ApiRecordRequest request) {
		String requestStr = new Gson().toJson(request);
		log.info("RECORD REQUEST: {}", requestStr);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path = "/video_call/answer_url")
	public @ResponseBody
	List getScco(ApiSccoRequest request) {
		String requestStr = new Gson().toJson(request);
		log.info("SCCO REQUEST: {}", requestStr);

		MyStorage.getInstance().addRecord(SccoRecord.builder()
						.id(MyStorage.getInstance().getListRecord().size() + 1)
						.userId(request.getTo())
						.callId(request.getCallId())
						.callTo(request.getFrom())
						.customerData(request.getCustom())
						.build());
		MyStorage.getInstance().addRecord(
				SccoRecord.builder()
						.id(MyStorage.getInstance().getListRecord().size() + 1)
						.userId(request.getUserId())
						.callId(request.getCallId())
						.callTo(request.getTo())
						.customerData(request.getCustom())
						.build());
		Record record = Record.builder()
				.action("record")
				.eventUrl("https://demo-deploy-sv.herokuapp.com/api/v1/video_call/recording")
				.format("mp3")
				.build();
		From from = From
				.builder()
				.type("internal")
				.number(request.getFrom())
				.alias(request.getFrom())
				.build();
		To to = To
				.builder()
				.type("internal")
				.number(request.getTo())
				.alias(request.getTo())
				.build();
		ApiSccoResponse apiSccoResponse = ApiSccoResponse
				.builder()
				.action("connect")
				.customData("test-custom-data")
				.continueOnFail(false)
				.timeout(1000000)
				.from(from)
				.to(to)
				.build();
		List response = Arrays.asList(record, apiSccoResponse);
		log.info("SCCO RESPONSE: {}", new Gson().toJson(response));
		return response;
	}

	@GetMapping(path = "/video_call/record_list")
	@CrossOrigin
	public ResponseEntity<List<SccoRecord>> getRecordLid() {
		return new ResponseEntity<>(MyStorage.getInstance().getListRecord(), HttpStatus.OK);
	}

	@GetMapping(path = "/video_call/record_download")
	@CrossOrigin
	public String downloadRecord(@RequestParam(name = "recordId") Integer recordId) {

		RestTemplate restTemplate = new RestTemplate();
		SccoRecord sccoRecord = MyStorage.getInstance().getListRecord()
				.stream()
				.filter(o -> o.getId() == recordId)
				.findFirst().get();

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("userId", sccoRecord.getUserId());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(jsonObject.toString(), headers);

		ResponseEntity<ApiTokenResponse> tokenResponse = restTemplate
				.exchange(tokenUri, HttpMethod.POST, entity, ApiTokenResponse.class);

		Map<String, String> urlParams = new HashMap<>();
		urlParams.put("call_id", sccoRecord.getCallId());
		urlParams.put("user_id", sccoRecord.getUserId());
		urlParams.put("access_token", tokenResponse.getBody().getToken());

		/*HttpHeaders headers2 = new HttpHeaders();
		headers2.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));

		HttpEntity<?> entity2 = new HttpEntity<>(urlParams, headers2);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(recordUri)
				.queryParam("call_id", sccoRecord.getCallId())
				.queryParam("user_id", sccoRecord.getUserId())
				.queryParam("access_token", tokenResponse.getBody().getToken());
		ResponseEntity<byte[]> record = restTemplate.exchange(
				builder.toUriString(),
				HttpMethod.GET,
				entity2,
				byte[].class);*/
		String recordUri = "https://api.stringee.com/v1/stringeecall2/recording";
		recordUri = recordUri + "?call_id=" + sccoRecord.getCallId() + "&user_id=" + sccoRecord.getUserId() + "&access_token=" + tokenResponse.getBody().getToken();
		return recordUri;
		//return new ResponseEntity<>(record, HttpStatus.OK);
	}

	@PostMapping(path = "/video_call/event_url")
	@CrossOrigin
	public ResponseEntity receiveCallEvent(@RequestBody CallEventRequest request) {
		String requestStr = new Gson().toJson(request);
		log.info("EVENT REQUEST: {}", requestStr);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
