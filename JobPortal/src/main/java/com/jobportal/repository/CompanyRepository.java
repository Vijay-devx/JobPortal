package com.jobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobportal.entities.Company;
import com.jobportal.entities.Industry;

import com.jobportal.dtos.CompanyRespDTO;

public interface CompanyRepository extends JpaRepository<Company, Long> {

	List<Company>findByIndustryAndLocation(Industry industry, String location);

}
