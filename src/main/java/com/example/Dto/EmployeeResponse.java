package com.example.Dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EmployeeResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Double salary;
    private String departmentName;
    private LocalDate dateOfBirth;
    private LocalDate dateOfJoining;
    private String employeeType;
    private Integer experienceYears;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    
 

    public EmployeeResponse(Long id, String firstName, String lastName, String email, Double salary,
			String departmentName, LocalDate dateOfBirth, LocalDate dateOfJoining, String employeeType,
			Integer experienceYears, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.salary = salary;
		this.departmentName = departmentName;
		this.dateOfBirth = dateOfBirth;
		this.dateOfJoining = dateOfJoining;
		this.employeeType = employeeType;
		this.experienceYears = experienceYears;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public EmployeeResponse() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }


    public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public LocalDate getDateOfJoining() {
		return dateOfJoining;
	}


	public void setDateOfJoining(LocalDate dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}


	public String getEmployeeType() {
		return employeeType;
	}


	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}


	public Integer getExperienceYears() {
		return experienceYears;
	}


	public void setExperienceYears(Integer experienceYears) {
		this.experienceYears = experienceYears;
	}


	public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}


	
}
