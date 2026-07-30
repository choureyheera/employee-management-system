package com.example.Service;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.Dto.EmployeeRequest;
import com.example.Dto.EmployeeResponse;
import com.example.Entity.Department;
import com.example.Entity.Employee;
import com.example.Entity.EmployeeType;
import com.example.Exception.DuplicateResourceException;
import com.example.Exception.ResourceNotFoundException;
import com.example.Repository.DepartmentRepository;
import com.example.Repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	private final EmployeeRepository employeeRepository;

    private final DepartmentRepository departmentRepository;


    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               DepartmentRepository departmentRepository) {

        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }


   //Saving EMP
    public EmployeeResponse saveEmployee(EmployeeRequest request) {
    	
    	if(employeeRepository.existsByEmail(request.getEmail())) {

            throw new DuplicateResourceException( "Email already exists");       
        }
    	
        // 1. DTO TO Entity 
        Employee employee = new Employee();
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setSalary(request.getSalary());
        employee.setDateOfBirth(request.getDateOfBirth());
        employee.setDateOfJoining(request.getDateOfJoining());
        
        //convert karna Converting String to enum
        employee.setEmployeeType(
                EmployeeType.valueOf(request.getEmployeeType()));
       
        employee.setExperienceYears(
                request.getExperienceYears());
        
        // 2.fetching from  Department 
        Department department =
                departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(
                    () -> new ResourceNotFoundException("Department not found"));
                
        // 3. Mapping (Relationship set karna)
        employee.setDepartment(department);

        // 4. saving into Database 
        Employee savedEmployee =
                employeeRepository.save(employee);

        // 5. Entity -> Response DTO
        EmployeeResponse response = new EmployeeResponse();
        response.setId(savedEmployee.getId());
        response.setFirstName(savedEmployee.getFirstName());
        response.setLastName(savedEmployee.getLastName());
        response.setEmail(savedEmployee.getEmail());
        response.setSalary(savedEmployee.getSalary());
        response.setDepartmentName(savedEmployee.getDepartment().getName());
        response.setDateOfBirth(savedEmployee.getDateOfBirth());
        response.setDateOfJoining(savedEmployee.getDateOfJoining());
        response.setEmployeeType(savedEmployee.getEmployeeType().name());
        response.setExperienceYears(savedEmployee.getExperienceYears());
        response.setCreatedAt(savedEmployee.getCreatedAt());
        response.setUpdatedAt(savedEmployee.getUpdatedAt());
        return response;
    }


    //Get all emp by "ID"
    public EmployeeResponse getEmployeeById(Long id) {
    	Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        EmployeeResponse response = new EmployeeResponse();
        response.setId(employee.getId());
        response.setFirstName(employee.getFirstName());
        response.setLastName(employee.getLastName());
        response.setEmail(employee.getEmail());
        response.setSalary(employee.getSalary());
        response.setDepartmentName(employee.getDepartment().getName());
        response.setDateOfBirth(employee.getDateOfBirth());
        response.setDateOfJoining(employee.getDateOfJoining());
        response.setEmployeeType(employee.getEmployeeType().name());
        response.setExperienceYears(employee.getExperienceYears());
        return response;
    }
   
  //Get all emp
    public List<EmployeeResponse> getAllEmployees(int page, int size, String sortBy,
            String sortDir) {
    	Sort sort = sortDir.equalsIgnoreCase("asc")
    	        ? Sort.by(sortBy).ascending()
    	        : Sort.by(sortBy).descending();
    	//page=1-1=0 else it goes in the (0-1= (-1))
	    	if(page <= 0){
	    	    page = 1;
	    	}
    	 Pageable pageable = PageRequest.of(page-1, size, sort);
    			
    	 	
    	    Page<Employee> employeePage = employeeRepository.findAll(pageable);
    	return employeePage.getContent().stream()
                .map(employee -> {
                    EmployeeResponse response = new EmployeeResponse();
                    response.setId(employee.getId());
                    response.setFirstName(employee.getFirstName());
                    response.setLastName(employee.getLastName());
                    response.setEmail(employee.getEmail());
                    response.setSalary(employee.getSalary());
                    response.setDepartmentName(employee.getDepartment().getName());
                    response.setDateOfBirth(employee.getDateOfBirth());
                    response.setDateOfJoining(employee.getDateOfJoining());
                    response.setEmployeeType(employee.getEmployeeType().name());
                    response.setExperienceYears(employee.getExperienceYears());
                    return response;  }) .toList();
    }
               
  //Update  emp by "ID"
    public EmployeeResponse updateEmployee(Long id, EmployeeRequest request) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setSalary(request.getSalary());
        employee.setDateOfBirth(request.getDateOfBirth());
        employee.setDateOfJoining(request.getDateOfJoining());
        employee.setEmployeeType(EmployeeType.valueOf(request.getEmployeeType()));
        employee.setExperienceYears(request.getExperienceYears());

        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

        employee.setDepartment(department);

        Employee updatedEmployee = employeeRepository.save(employee);

        EmployeeResponse response = new EmployeeResponse();
        response.setId(updatedEmployee.getId());
        response.setFirstName(updatedEmployee.getFirstName());
        response.setLastName(updatedEmployee.getLastName());
        response.setEmail(updatedEmployee.getEmail());
        response.setSalary(updatedEmployee.getSalary());
        response.setDepartmentName(updatedEmployee.getDepartment().getName());
        response.setDateOfBirth(updatedEmployee.getDateOfBirth());
        response.setDateOfJoining(updatedEmployee.getDateOfJoining());
        response.setEmployeeType(updatedEmployee.getEmployeeType().name());
        response.setExperienceYears(updatedEmployee.getExperienceYears());

        return response;
    }	
    
    
    //delete by "ID"
	    public void deleteEmployee(Long id) {
	    	 Employee employee = employeeRepository.findById(id)
	    	            .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
	    	    employeeRepository.delete(employee);
	    }
	    
	    //filtering
	    //Search by emp first name
	    public List<EmployeeResponse> searchByFirstName(String firstName) {

	        List<Employee> employees =
	                employeeRepository.findByFirstNameContainingIgnoreCase(firstName);

	        return employees.stream()
	                .map(employee -> {

	                    EmployeeResponse response = new EmployeeResponse();

	                    response.setId(employee.getId());
	                    response.setFirstName(employee.getFirstName());
	                    response.setLastName(employee.getLastName());
	                    response.setEmail(employee.getEmail());
	                    response.setSalary(employee.getSalary());
	                    response.setDateOfBirth(employee.getDateOfBirth());
	                    response.setDateOfJoining(employee.getDateOfJoining());
	                    response.setEmployeeType(employee.getEmployeeType().name());
	                    response.setExperienceYears(employee.getExperienceYears());
	                    response.setDateOfBirth(employee.getDateOfBirth());
	                    response.setDateOfJoining(employee.getDateOfJoining());
	                    response.setEmployeeType(employee.getEmployeeType().name());
	                    response.setExperienceYears(employee.getExperienceYears());
	                    return response;}).toList();

	    }
	    
	 
	  //Search by dept
	    public List<EmployeeResponse> searchByDepartment(String departmentName) {

	        List<Employee> employees =
	                employeeRepository.findByDepartment_NameContainingIgnoreCase(departmentName);

	        return employees.stream()
	                .map(employee -> {

	                    EmployeeResponse response = new EmployeeResponse();

	                    response.setId(employee.getId());
	                    response.setFirstName(employee.getFirstName());
	                    response.setLastName(employee.getLastName());
	                    response.setEmail(employee.getEmail());
	                    response.setSalary(employee.getSalary());
	                    response.setDateOfBirth(employee.getDateOfBirth());
	                    response.setDateOfJoining(employee.getDateOfJoining());
	                    response.setEmployeeType(employee.getEmployeeType().name());
	                    response.setExperienceYears(employee.getExperienceYears());
	                    response.setDepartmentName(employee.getDepartment().getName());
	                    return response;}).toList();
	        }

}
