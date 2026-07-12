package com.jobportal.dtos;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CompanyRespDTO {
	private Long companyId;
	private String companyName;
	private String email;
}
