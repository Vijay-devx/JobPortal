package com.jobportal.entities;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//Job (Represents a job opening posted by a company)
//Attributes
//-	id (Long, PK)  - auto increment
//-	title 
//-	description
//-	salary
//-	location
//-	jobType (enum FULL_TIME / PART_TIME / CONTRACT)
//-	postedDate
//-        status (enum - AVAILABLE | UNAVAILABLE)
//-	company (Many-to-One)

@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "jobCompany")
@Entity
@Table(name = "job")
public class Job {
	@Id
	@Column(name="job_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long jobId;
	@Column(length=30)
	private String title;
	@Column(length=100)
	private String description;
	private Double salary;
	@Column(length=30)
	private String location;
	@Enumerated(EnumType.STRING)
	private JobType jobType;
	@CreationTimestamp
	@Column(name = "posted_on")
	private LocalDate postedDate;
	@Enumerated(EnumType.STRING)
	private JobStatus status;
//	Job *----->1company
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "company_id")
	private Company jobCompany;

}



























