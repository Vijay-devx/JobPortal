package com.jobportal.service;

import java.util.List;

import com.jobportal.dtos.JobReqDTO;
import com.jobportal.dtos.JobRespDTO;
import com.jobportal.dtos.UpdateJobStatusReqDTO;
import com.jobportal.entities.Industry;
import com.jobportal.entities.JobType;

public interface JobService {

	ApiResponse addNewJob(JobReqDTO request);

	List<JobRespDTO> getAllJobsOpeningsByLocation(String location);

	String deleteJobsByJobTypeAndCompanyName(JobType jobType, String companyName);

	String updateJobSalaryByCompanyIdAndJobTitle(Long companyId, String jobTitle, Double salary);

	List<JobRespDTO> getAllJobsByIndustryTypeAndMinSalary(Industry industryType, Double minSalary);

	String updateJobStatusByCompanyNameAndJobTitle(UpdateJobStatusReqDTO reqDto);

	String deleteCompanyById(Long companyId);

}
