package com.jobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jobportal.entities.Industry;
import com.jobportal.entities.Job;
import com.jobportal.entities.JobType;

public interface JobRepository extends JpaRepository<Job, Long>{

	List<Job> findByLocation(String location);

	int deleteByJobTypeAndJobCompanyCompanyName(JobType jobType, String companyName);

	List<Job> findByJobCompanyCompanyIdAndTitle(Long companyId, String jobTitle);

	List<Job> findByJobCompanyIndustryAndSalaryGreaterThanEqual(Industry industryType, Double minSalary);
	
	@Modifying
	@Query("""
			update Job j
			set j.status = 'UNAVAILABLE'
			where j.jobCompany.companyName=:companyName
			and j.title=:title 
			""")
	int updateJobStatusToUnavailable(
			@Param("companyName") String companyName,
			@Param("title") String title);

	int deleteByJobCompanyCompanyId(Long companyId);
	
}


















