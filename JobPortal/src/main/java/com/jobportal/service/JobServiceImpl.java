package com.jobportal.service;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobportal.dtos.JobReqDTO;
import com.jobportal.dtos.JobRespDTO;
import com.jobportal.dtos.UpdateJobStatusReqDTO;
import com.jobportal.entities.Company;
import com.jobportal.entities.Industry;
import com.jobportal.entities.Job;
import com.jobportal.entities.JobType;
import com.jobportal.repository.CompanyRepository;
import com.jobportal.repository.JobRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
	
	public final JobRepository jobRepo;
	public final CompanyRepository companyRepo;
	public final ModelMapper mapper;
	

	@Override
	public ApiResponse addNewJob(JobReqDTO reqDto) {
		
			Company company = companyRepo.findById(reqDto.getCompanyId()).orElseThrow(
					()-> new RuntimeException());
				
			 Job newJob = mapper.map(reqDto, Job.class);
			newJob.setJobCompany(company);
			jobRepo.save(newJob);
			return new ApiResponse("success", "Job Posted Successfully");
			
	}


	@Override
	public List<JobRespDTO> getAllJobsOpeningsByLocation(String location) {
		List<Job> jobs = jobRepo.findByLocation(location);
		
		if(jobs.isEmpty()) {
			throw new RuntimeException("no jobs found for the location : "+location);
		}
		
		return jobs.stream()
				.map(job -> {
					JobRespDTO dto = mapper.map(job, JobRespDTO.class);
					/*dto.setCompanyId(job.getJobCompany().getCompanyId());*/
					return dto;
					})
				.toList();
	}


	@Override
	public String deleteJobsByJobTypeAndCompanyName(JobType jobType, String companyName) {
		int rowCount = jobRepo.deleteByJobTypeAndJobCompanyCompanyName(jobType, companyName);
		
		if(rowCount == 0) {
			throw new RuntimeException("Failed to delete, No matching jobs found");
		}
		
		return rowCount+" jobs deleted successfully";
	}


	@Override
	public String updateJobSalaryByCompanyIdAndJobTitle(Long companyId, String jobTitle, Double salary) {
		List<Job> jobs = jobRepo.findByJobCompanyCompanyIdAndTitle(companyId, jobTitle);
		
		if(jobs == null) {
			throw new RuntimeException(
					String.format(
							"No jobs exist for the companyId : %d and jobTitle: %s",
							companyId,
							jobTitle
							));
		}
		
		jobs = jobs.stream()
				.map(job -> {
					job.setSalary(salary);
					return job;
				})
				.toList();
		jobRepo.saveAll(jobs);
		return "Salary updated Successfully";
	}


	@Override
	public List<JobRespDTO> getAllJobsByIndustryTypeAndMinSalary(Industry industryType, Double minSalary) {
		//get all jobs
		List<Job> jobs = jobRepo.findByJobCompanyIndustryAndSalaryGreaterThanEqual(industryType, minSalary);
		//Throw exception if list is empty
		if(jobs.isEmpty()) {
			throw new RuntimeException("No Such Jobs exists");
		}
		// map them with jobRespDTO 
		//return the jobRespDTOList
		return jobs.stream()
				.map(job->{
					JobRespDTO dto = mapper.map(job, JobRespDTO.class);
					return dto;
				})
				.toList();
		
	}


	@Override
	public String updateJobStatusByCompanyNameAndJobTitle(UpdateJobStatusReqDTO reqDto) {
		int rowCount = jobRepo.updateJobStatusToUnavailable(reqDto.getCompanyName(), reqDto.getTitle());
		if(rowCount == 0) {
			throw new RuntimeException("No Such Jobs Exist");
		}
		return rowCount+" Jobs Updated Successfully";
	}


	@Override
	public String deleteCompanyById(Long companyId) {
		if(!companyRepo.existsById(companyId)) {
			throw new RuntimeException("Company Not Found");
		}
		//delete jobs first
		jobRepo.deleteByJobCompanyCompanyId(companyId);
		//then delete company
		companyRepo.deleteById(companyId);
		
		return "Company deleted successfully";
		
	}

}




























