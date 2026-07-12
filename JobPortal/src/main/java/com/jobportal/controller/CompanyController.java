package com.jobportal.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.entities.Company;
import com.jobportal.service.CompanyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {
	public final CompanyService companyService;
	/*
	 * URL-http://localhost:post/company/list
	 * @RequestBody
	 * method-POST
	 * i/p: industry type and location
	 * o/p: list<companies>
	 * 
	 */
	
	@PostMapping("/list")
	public ResponseEntity<?> getCompanyByTypeAndLocation(@RequestBody Company request){
		try{
			return ResponseEntity.ok(companyService.getCompanyDetails(request));
			
		}catch(RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
}

























