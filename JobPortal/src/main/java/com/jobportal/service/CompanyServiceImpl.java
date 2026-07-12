package com.jobportal.service;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jobportal.dtos.CompanyRespDTO;
import com.jobportal.entities.Company;
import com.jobportal.repository.CompanyRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
	
    private final ModelMapper mapper;
	private final CompanyRepository companyRepo;

	@Override
	public List<CompanyRespDTO> getCompanyDetails(Company request) {
		List<Company> companies = companyRepo.findByIndustryAndLocation(request.getIndustry(), request.getLocation());
		
		return companies.stream()
				.map(company -> mapper.map(company,CompanyRespDTO.class))
				.toList();
	}

}



















