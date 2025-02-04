package com.ust.project_service.client;


import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ust.DTO.Employee;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@FeignClient(name = "employee-service", url = "http://localhost:3030/api/employees")
public interface EmployeeClient {
    @GetMapping("/projects/{projectId}")
    List<Employee> getEmployeesByProjectId(@PathVariable ("projectId") String projectId);

     @GetMapping("/unassigned")
    List<Employee> getUnassignedEmployees();

    @PutMapping("/update-status")
    void updateEmployeesStatus(@RequestBody List<String> employeeIds, @RequestParam String projectId);

    
}
