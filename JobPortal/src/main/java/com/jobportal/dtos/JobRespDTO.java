package com.jobportal.dtos;

import java.time.LocalDate;


import com.jobportal.entities.Company;
import com.jobportal.entities.JobStatus;
import com.jobportal.entities.JobType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JobRespDTO {
	private Long jobId;
	private String title;
	private String description;
	private Double salary;
	private String location;
	private JobType jobType;
	private LocalDate postedDate;
	private JobStatus status;
	private Company jobCompany;

}
