package com.jobportal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.dtos.JobReqDTO;
import com.jobportal.dtos.UpdateJobStatusReqDTO;
import com.jobportal.entities.Industry;
import com.jobportal.entities.JobType;
import com.jobportal.service.ApiResponse;
import com.jobportal.service.JobService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController {
	public final JobService jobService;
	
	/*
	 * URL: http://localhost:8080/jobs/add
	 * i/p: company id , title , description ,salary,location, jobType
	 * @RequestBody
	 * method - POST
	 * o/p: SC 200 + message("record added successfully")
	 *  
	 */
	
	@PostMapping("/add")
	public ResponseEntity<?> addNewJob(@Valid @RequestBody JobReqDTO request){
		try {
			return ResponseEntity.ok(jobService.addNewJob(request));
			
		}catch(RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}	
	}
	
//	List all job openings posted by companies , from the specified location
//	Input  - location
	
	/*
	 * URL: http://localhost:8080/jobs/{location}
	 * i/p:location
	 * @PathVariable
	 * o/p: list<JobDTO>
	 * 
	 */
	@GetMapping("/{location}")
	public ResponseEntity<?> getAllJobsOpeningsByLocation(@PathVariable String location){
		try {
			return ResponseEntity.ok(jobService.getAllJobsOpeningsByLocation(location));
		}
		catch(RuntimeException e) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse("Error", e.getMessage()));
		}
	}
	
//	Delete all jobs of the specified job type , of specified company
//	 Input - job type , company name
	
	/*
	 * URL: http://localhost:8080/jobs/
	 * method: @DeleteMapping
	 * i/p: @RequestBody job_type, company_name
	 * o/p: "delete success message
	 */
	
	@DeleteMapping("/{jobType}/{companyName}")
	public ResponseEntity<?> deleteJobsByJobTypeAndCompanyName(
			@PathVariable JobType jobType,
			@PathVariable String companyName ){
		
		try {
			
			return ResponseEntity
					.ok(new ApiResponse("Success",jobService.deleteJobsByJobTypeAndCompanyName(jobType, companyName)));
			
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ApiResponse("Failed", e.getMessage()));
		}
		
	}
	
//	Update salaries of the job , posted by a specific company , for specific job title
//	Input - company id , job title
	
	/*
	 * URL: http://localhost:8080/jobs/
	 * method: PUT -->@PutMapping
	 * i/p: company id, job title , salary
	 * @PathVariable
	 * o/p: success message
	 */
	
	@PutMapping("/{companyId}/{jobTitle}/{salary}")
	public ResponseEntity<?> updateJobSalaryByCompanyIdAndJobTitle(
			@PathVariable Long companyId,
			@PathVariable String jobTitle,
			@PathVariable double salary){
		try {
			
			return ResponseEntity
					.ok(new ApiResponse("Success",jobService
							.updateJobSalaryByCompanyIdAndJobTitle(companyId, jobTitle, salary)));
			
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ApiResponse("Failed", e.getMessage()));
		}
	}
	
//	List all the jobs , from specific industry type & having minimum specified salary.
//	Input - industry type , min salary
	/*
	 * URL: http://localhost:8080/jobs/industyType/minSalary
	 * method: GET @GetMapping
	 * i/p: industry type, min salary
	 * o/p: list<JobRespDTO>
	 */
	
	@GetMapping("/{industryType}/{minSalary}")
	public ResponseEntity<?> getAllJobsByIndustryTypeAndMinSalary(
			@PathVariable Industry industryType,
			@PathVariable Double minSalary){
		try {
			return ResponseEntity.ok(jobService
					.getAllJobsByIndustryTypeAndMinSalary(industryType, minSalary));
		}
		catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse("Failed",e.getMessage()));
		}
	}
	
//	Company needs to update a job status to unavailable (position filled)
//	Input - company name & job title
	/*
	 * URL:http://localhost:8080/jobs/status
	 * method: PUT @PutMapping
	 * i/p: companyName, job title
	 * o/p: success message
	 */
	
	@PutMapping("/status")
	public ResponseEntity<?> updateJobStatusByCompanyNameAndJobTitle(
			@RequestBody UpdateJobStatusReqDTO reqDto){
		try {
			
			return ResponseEntity
					.ok(new ApiResponse("Success",
							jobService.updateJobStatusByCompanyNameAndJobTitle(reqDto)) 
							);
			
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ApiResponse("Failed", e.getMessage()));
		}
	}
	
//	Delete Company details
//	- Input : company id
	/*
	 * URL: http://localhost:8080/jobs/companyId
	 * method: Delete @DeleteMapping
	 * i/p: companyId
	 * o/p: SC 200 + success message
	 */
	
	@DeleteMapping("/{companyId}")
	public ResponseEntity<?> deleteCompanyById(@PathVariable Long companyId){
		try {
			
			return ResponseEntity
					.ok(new ApiResponse("Success", 
							jobService.deleteCompanyById(companyId)));
			
		}catch(RuntimeException e) {
			return ResponseEntity.badRequest()
					.body(new ApiResponse("Failed", e.getMessage()));
		}
	}
	
}





































