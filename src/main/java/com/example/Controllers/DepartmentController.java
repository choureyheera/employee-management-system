package com.example.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Dto.DepartmentRequest;
import com.example.Dto.DepartmentResponse;
import com.example.Dto.EmployeeResponse;
import com.example.Service.DepartmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

	  private final DepartmentService departmentService;
	  public DepartmentController(DepartmentService departmentService) {
		  this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<DepartmentResponse> saveDepartment(
    		@Valid  @RequestBody DepartmentRequest request) {
        DepartmentResponse response =
                departmentService.saveDepartment(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);               
    }


    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponse> getDepartmentById(
            @PathVariable Long id) {
       DepartmentResponse response =
                departmentService.getDepartmentById(id);
       return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentResponse>> getAllDepartments() {

    	return ResponseEntity.ok(
                departmentService.getAllDepartments());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(
            @PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.ok(
                "Department deleted successfully");
        }
    
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponse> updateDepartment(
            @PathVariable Long id, @Valid 
            @RequestBody DepartmentRequest request) {
        return ResponseEntity.ok(
                departmentService.updateDepartment(id, request));
       }
    
    
    
}
