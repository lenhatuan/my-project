package com.eureka.eurekaservice;

import com.eureka.eurekaservice.dto.*;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
@Slf4j
public class ExternalController {

	@PostMapping(path = "/video_call/token")
	@CrossOrigin
	public ResponseEntity<ApiTokenResponse> getToken(@RequestBody ApiTokenRequest request) {
		String token = TokenUtil.genAccessToken(request.getUserId(),
				"SKnisBolJVGOhRoa61exg48aNhA6N5qpRb",
				"MGc4N3dWSWg1Rzd6YXdqMGx3c1ZTRmI2TXRNUWZZ",
				3600);
		return new ResponseEntity<>(ApiTokenResponse.builder().token(token).build(), HttpStatus.OK);
	}

	@GetMapping(path = "/video_call/answer_url")
	public @ResponseBody
	List<ApiSccoResponse> getScco(ApiSccoRequest request) {
		String requestStr = new Gson().toJson(request);
		log.info("SCCO REQUEST: {}", requestStr);
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
				.timeout(100)
				.from(from)
				.to(to)
				.build();
		return Arrays.asList(apiSccoResponse);
	}
}
