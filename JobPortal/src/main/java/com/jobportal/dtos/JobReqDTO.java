package com.jobportal.dtos;

import com.jobportal.entities.JobType;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JobReqDTO {
	@NotNull(message = "campany Id can NOT be null")
	private Long companyId;
	private String title;
	private String description;
	private Double salary;
	private String location;
	private JobType jobType;
	
}
