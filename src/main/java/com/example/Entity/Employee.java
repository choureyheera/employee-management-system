package com.example.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;


@Entity
@Table(name = "Employees")
public class Employee {
	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	 	private String firstName;
	    private String lastName;
	    
	    @Column(unique = true)
	    private String email;
	    private Double salary;
	    private LocalDate dateOfBirth;
	    private LocalDate dateOfJoining;
	    
	    @Enumerated(EnumType.STRING)
	    private EmployeeType employeeType;
	    private Integer experienceYears;
	    
	    @Column(updatable = false)
	    private LocalDateTime createdAt;

	    @Column(insertable = false)
	    private LocalDateTime updatedAt;

//	    
//	    //soft dlt
//	    @Column(nullable = false)
//	    private boolean deleted = false;
	    
	    
	    @ManyToOne
	    @JoinColumn(name = "department_id")
	    private Department department;
	    
	    @PrePersist
	    public void onCreate() {
	        createdAt = LocalDateTime.now();
	        updatedAt = LocalDateTime.now();
	    }


	    @PreUpdate
	    public void onUpdate() {
	        updatedAt = LocalDateTime.now();
	    }
	    
	    public Employee() {}
	    

		public Employee(Long id, String firstName, String lastName, String email, Double salary, LocalDate dateOfBirth,
				LocalDate dateOfJoining, EmployeeType employeeType, Integer experienceYears, LocalDateTime createdAt,
				LocalDateTime updatedAt, Department department) {
			super();
			this.id = id;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.salary = salary;
			this.dateOfBirth = dateOfBirth;
			this.dateOfJoining = dateOfJoining;
			this.employeeType = employeeType;
			this.experienceYears = experienceYears;
			this.createdAt = createdAt;
			this.updatedAt = updatedAt;
			this.department = department;
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


		public EmployeeType getEmployeeType() {
			return employeeType;
		}


		public void setEmployeeType(EmployeeType employeeType) {
			this.employeeType = employeeType;
		}


		public Integer getExperienceYears() {
			return experienceYears;
		}


		public void setExperienceYears(Integer experienceYears) {
			this.experienceYears = experienceYears;
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


		public Department getDepartment() {
			return department;
		}


		public void setDepartment(Department department) {
			this.department = department;
		}


		@Override
		public String toString() {
			return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
					+ ", salary=" + salary + ", dateOfBirth=" + dateOfBirth + ", dateOfJoining=" + dateOfJoining
					+ ", employeeType=" + employeeType + ", experienceYears=" + experienceYears + ", createdAt="
					+ createdAt + ", updatedAt=" + updatedAt + ", department=" + department + "]";
		}
	    
	    
}
