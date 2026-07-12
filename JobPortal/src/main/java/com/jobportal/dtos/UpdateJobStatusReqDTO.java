package com.jobportal.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateJobStatusReqDTO {
	private String companyName;
	private String title;
}
