package com.eureka.eurekaservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ApiTokenResponse {

	private int resultCode;

	private String resultMessage;

	private String token;

}
