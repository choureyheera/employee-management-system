package com.example.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.Dto.DepartmentRequest;
import com.example.Dto.DepartmentResponse;
import com.example.Dto.EmployeeResponse;
import com.example.Entity.Department;
import com.example.Entity.Employee;
import com.example.Exception.DuplicateResourceException;
import com.example.Exception.ResourceNotFoundException;
import com.example.Repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	 private final DepartmentRepository departmentRepository;
	private DepartmentRepository employeeRepository;


	    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
	        this.departmentRepository = departmentRepository;
	    }
	    
	    public DepartmentResponse saveDepartment(DepartmentRequest request) {

	        if (departmentRepository.existsByName(request.getName())) {
	            throw new DuplicateResourceException("Department already exists");
	        }

	        Department department = new Department();

	        department.setName(request.getName());
	        department.setDescription(request.getDescription());

	        Department savedDepartment = departmentRepository.save(department);

	        DepartmentResponse response = new DepartmentResponse();
	        response.setId(savedDepartment.getId());
	        response.setName(savedDepartment.getName());
	        response.setDescription(savedDepartment.getDescription());

	        return response;
	    }
	    
	   //Getting all data from db by ID
	    public DepartmentResponse getDepartmentById(Long id) {
	        Department department =
	                departmentRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
	                
	        DepartmentResponse response = new DepartmentResponse();
	        response.setId(department.getId());
	        response.setName(department.getName());
	        response.setDescription(department.getDescription());
	        return response;
	    }

	    //Getting all data from db 
	    public List<DepartmentResponse> getAllDepartments() {
	        List<Department> departments =
	                departmentRepository.findAll();

	        return departments.stream()
	                .map(department -> {
	                    DepartmentResponse response =
	                            new DepartmentResponse();
	                    response.setId(department.getId());
	                    response.setName(department.getName());
	                    response.setDescription(department.getDescription());
	                    return response;
	                })   .collect(Collectors.toList());
	              }
	    
	    //data is deleting by id
	    public void deleteDepartment(Long id) {

	        departmentRepository.deleteById(id);
	    }
	    
	   
	    public DepartmentResponse updateDepartment(Long id, DepartmentRequest request) {

	        Department department = departmentRepository.findById(id)
	             .orElseThrow(()-> new ResourceNotFoundException("Department not found"));

	        department.setName(request.getName());
	        department.setDescription(request.getDescription());
	        Department updatedDepartment = departmentRepository.save(department);
	        
	        
	        DepartmentResponse response = new DepartmentResponse();
	        response.setId(updatedDepartment.getId());
	        response.setName(updatedDepartment.getName());
	        response.setDescription(updatedDepartment.getDescription());

	        return response;
	    }
	    
	    
}
