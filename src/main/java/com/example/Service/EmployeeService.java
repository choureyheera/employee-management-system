package com.example.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Dto.EmployeeRequest;
import com.example.Dto.EmployeeResponse;


public interface EmployeeService {


    EmployeeResponse saveEmployee(EmployeeRequest request);

    EmployeeResponse getEmployeeById(Long id);

    List<EmployeeResponse> getAllEmployees(int page, int size,String sortBy,
            String sortDir);

    void deleteEmployee(Long id);
    
    EmployeeResponse updateEmployee(Long id, EmployeeRequest request);

    
    //filtering
    List<EmployeeResponse> searchByFirstName(String firstName);
   List<EmployeeResponse> searchByDepartment(String departmentName);

}

