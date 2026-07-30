package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	boolean existsByEmail(String email);
	
	//filtering
	List<Employee> findByFirstNameContainingIgnoreCase(String firstName);

	List<Employee> findByDepartment_NameContainingIgnoreCase(String departmentName);

 
}
