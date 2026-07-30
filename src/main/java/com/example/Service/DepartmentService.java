package com.example.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Dto.DepartmentRequest;
import com.example.Dto.DepartmentResponse;
import com.example.Dto.EmployeeResponse;

public interface DepartmentService {


    DepartmentResponse saveDepartment(DepartmentRequest request);
    
    DepartmentResponse getDepartmentById(Long id);

    List<DepartmentResponse> getAllDepartments();

    void deleteDepartment(Long id);
    
    DepartmentResponse updateDepartment(Long id, DepartmentRequest request);
    
    
}
