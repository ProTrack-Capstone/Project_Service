package com.ust.project_service.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ust.DTO.Employee;

import java.util.List;

@FeignClient(name = "employee-service", url = "http://localhost:3030/api/employees")
public interface EmployeeClient {
    @GetMapping("/projects/{projectId}")
    List<Employee> getEmployeesByProjectId(@PathVariable String projectId);
}
