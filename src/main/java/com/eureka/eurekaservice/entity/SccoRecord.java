package com.eureka.eurekaservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "scco_record", schema = "test")
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
