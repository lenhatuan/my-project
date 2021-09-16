package com.eureka.eurekaservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ApiSccoResponse {

	private String action;

	private String customData;

	private boolean continueOnFail;

	private long timeout;

	private From from;

	private To to;

}
