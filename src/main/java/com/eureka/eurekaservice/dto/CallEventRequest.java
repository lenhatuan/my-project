package com.eureka.eurekaservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CallEventRequest {

	@JsonProperty("call_status")
	private String callStatus;

	@JsonProperty("project_id")
	private String projectId;

	@JsonProperty("request_from_user_id")
	private String requestFromUserId;

	@JsonProperty("account_sid")
	private String accountSid;

	@JsonProperty("timestamp_ms")
	private long timestampMs;

	@JsonProperty("from")
	private From from;

	@JsonProperty("to")
	private To to;

	@JsonProperty("clientCustomData")
	private String clientCustomData;

	@JsonProperty("type")
	private String type;

	@JsonProperty("call_id")
	private String callId;

	@JsonProperty("callCreatedReason")
	private String callCreatedReason;

	@JsonProperty("endCallCause")
	private String endCallCause;

	@JsonProperty("endedBy")
	private String endedBy;

	@JsonProperty("callType")
	private String callType;

	@JsonProperty("duration")
	private long duration;

	@JsonProperty("answerDuration")
	private long answerDuration;
}
