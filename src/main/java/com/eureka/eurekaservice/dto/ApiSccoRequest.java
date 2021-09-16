package com.eureka.eurekaservice.dto;

import lombok.Data;
import lombok.Value;

@Data
@Value
public class ApiSccoRequest {

	private String from;

	private String to;

	private boolean fromInternal;

	private String userId;

	private String projectId;

	private String custom;

	private String callId;

}
