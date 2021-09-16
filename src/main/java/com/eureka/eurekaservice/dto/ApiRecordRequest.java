package com.eureka.eurekaservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ApiRecordRequest {

	private String start_time;
	private String end_time;
	private String recording_url;
	private String call_id;
	private String project_id;
	private String type;
	private String recordMessage;
}
