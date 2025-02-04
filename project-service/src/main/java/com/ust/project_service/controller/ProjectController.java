package com.ust.project_service.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ust.DTO.Employee;
import com.ust.project_service.client.EmployeeClient;
import com.ust.project_service.entity.Project;
import com.ust.project_service.service.ProjectService;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectService projectService;
    private final EmployeeClient employeeClient;

    public ProjectController(ProjectService projectService, EmployeeClient employeeClient) {
        this.projectService = projectService;
        this.employeeClient = employeeClient;
    }

    @PostMapping
    public ResponseEntity<Project> addProject(@RequestBody Project project) {
        return ResponseEntity.ok(projectService.addProject(project));
    }

    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<Project> getProjectById(@PathVariable String projectId) {
        return ResponseEntity.ok(projectService.getProjectById(projectId));
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<Project> updateProject(@PathVariable  String projectId, @RequestBody Project project) {
        return ResponseEntity.ok(projectService.updateProject(projectId, project));
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteProject(@PathVariable  String projectId) {
        projectService.deleteProject(projectId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{projectId}/employees")
    public ResponseEntity<List<Employee>> getEmployeesByProjectId(@PathVariable String projectId) {
        List<Employee> employees = projectService.getEmployeesByProjectId(projectId);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/projects/unassigned-employees")
    public ResponseEntity<List<Employee>> getUnassignedEmployees() {
        return ResponseEntity.ok(employeeClient.getUnassignedEmployees());
    }

    @PutMapping("/{projectId}/assign-employees")
    public ResponseEntity<String> assignEmployeesToProject(
            @PathVariable String projectId,
            @RequestBody List<String> employeeIds) {

        projectService.assignEmployees(projectId, employeeIds);
        return ResponseEntity.ok("Employees assigned successfully");
    }

    @GetMapping("/{projectId}/unassigned-employees")
    public ResponseEntity<List<Employee>> getUnassignedEmployeesForProject(@PathVariable String projectId) {
        List<Employee> employees = projectService.getUnassignedEmployees();
        return ResponseEntity.ok(employees);
    }
    

}
