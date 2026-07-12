package com.jobportal.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*

 * 
 *  Attributes 
 * - id (Long - PK) - auto increment 
 * - name (unique) 
 * - email (unique)
 * - location 
 * - industry (enum - Education , Pharmaceutical , Hospitality ,
 * Information Technology (IT) Banking , Healthcare) 
 * - created on : date
 */

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="company")
public class Company {
	@Id
	@Column(name="company_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long companyId;
	@Column(name="company_name", length=30, unique=true)
	private String companyName;
	@Column(length=50, unique=true)
	private String email;
	@Column(length=30)
	private String location;
	@Enumerated(EnumType.STRING)
	private Industry industry;
	@Column(name = "created_on")
	private LocalDate createdOn;
	
	public Company(String companyName, String email, String location, Industry industry) {
		super();
		this.companyName = companyName;
		this.email = email;
		this.location = location;
		this.industry = industry;
	}
}
































