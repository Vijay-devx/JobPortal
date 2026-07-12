package com.jobportal.service;

import java.util.List;

import com.jobportal.dtos.CompanyRespDTO;
import com.jobportal.entities.Company;

public interface CompanyService {

	List<CompanyRespDTO> getCompanyDetails(Company request);

}
