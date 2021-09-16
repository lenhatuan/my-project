package com.eureka.eurekaservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SccoRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String userId;

	private String callId;

	private String callTo;

	private String accessToken;

	private String customerData;

}
