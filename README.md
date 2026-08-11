# Job Portal Backend API

A robust RESTful API backend for a Job Portal application, built with Java 21 and Spring Boot. This application provides endpoints to manage companies, post job openings, and search for jobs based on various criteria such as location, industry, and salary.

## 🚀 Tech Stack
- **Java 21**
- **Spring Boot 3.5.x** (Web, Data JPA, Validation)
- **MySQL Database**
- **Hibernate / Spring Data JPA**
- **Lombok** (for boilerplate code reduction)
- **Springdoc OpenAPI (Swagger UI)** (for API documentation)
- **ModelMapper** (for DTO conversions)

## ✨ Features

### Job Management
- **Add Job**: Post a new job opening for a company.
- **Search by Location**: Retrieve all job openings in a specified location.
- **Search by Industry & Salary**: Find jobs matching a specific industry type with a minimum salary requirement.
- **Update Salary**: Update the salary for a specific job title at a given company.
- **Update Status**: Mark a job's status as unavailable once the position is filled.
- **Bulk Delete**: Delete all jobs of a specific type (e.g., FULL_TIME, PART_TIME) for a specific company.

### Company Management
- **List Companies**: Retrieve companies filtered by industry type and location.
- **Delete Company**: Remove a company and its associated job postings from the system.

## 🛠️ Setup & Installation

1. **Clone the repository:**
   ```bash
   git clone <repository-url>
