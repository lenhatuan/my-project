package com.eureka.eurekaservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SccoRecord {

	private Integer id;

	private String userId;

	private String callId;

	private String callTo;

	private String accessToken;

	private String customerData;

}
