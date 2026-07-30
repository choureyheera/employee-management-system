package com.example.Controllers;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Dto.EmployeeRequest;
import com.example.Dto.EmployeeResponse;
import com.example.Service.EmployeeService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/employees")
public class EmployeeController {

	
	private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Create Employee
    @PostMapping
    public ResponseEntity<EmployeeResponse> saveEmployee(@Valid
            @RequestBody EmployeeRequest request) {
        EmployeeResponse response = employeeService.saveEmployee(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get Employee By Id
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(
            @PathVariable Long id) {
        EmployeeResponse response = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(response);
        }

    // Get All Employees
    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees(
    		@RequestParam(defaultValue = "0") int page,
    		@RequestParam (defaultValue = "5") int size,
    		@RequestParam(defaultValue = "id") String sortBy,
    	    @RequestParam(defaultValue = "asc") String sortDir) {
    	
        List<EmployeeResponse> response = employeeService.getAllEmployees(page, size,sortBy,sortDir);
        return ResponseEntity.ok(response);
    }

    // Delete Employee
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(
            @PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(
            @PathVariable Long id,@Valid @RequestBody EmployeeRequest request) 
	         {
	        EmployeeResponse response = employeeService.updateEmployee(id, request);
	        return ResponseEntity.ok(response);
	    }
    //Filtering records
    @GetMapping("/search")
    public ResponseEntity<List<EmployeeResponse>> searchByFirstName(
            @RequestParam String firstName) {

        List<EmployeeResponse> response =
                employeeService.searchByFirstName(firstName);

        return ResponseEntity.ok(response);
    }
    
  //filtering
    @GetMapping("/search/department")
    public ResponseEntity<List<EmployeeResponse>> searchByDepartment(
            @RequestParam String departmentName) {

        List<EmployeeResponse> response =
                employeeService.searchByDepartment(departmentName);

        return ResponseEntity.ok(response);
    }
    
}
