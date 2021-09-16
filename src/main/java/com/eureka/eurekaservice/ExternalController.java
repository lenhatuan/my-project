package com.eureka.eurekaservice;

import com.eureka.eurekaservice.dto.ApiSccoRequest;
import com.eureka.eurekaservice.dto.ApiSccoResponse;
import com.eureka.eurekaservice.dto.From;
import com.eureka.eurekaservice.dto.To;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
public class ExternalController {

	@GetMapping(path = "/video_call/answer_url")
	public @ResponseBody
	List<ApiSccoResponse> getScco(ApiSccoRequest request) {
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
