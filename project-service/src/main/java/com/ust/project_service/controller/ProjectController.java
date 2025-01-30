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
import com.ust.project_service.entity.Project;
import com.ust.project_service.service.ProjectService;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<Project> addProject(@RequestBody Project project) {
        return ResponseEntity.ok(projectService.addProject(project));
    }

    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable("id") String projectId) {
        return ResponseEntity.ok(projectService.getProjectById(projectId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable ("id") String projectid, @RequestBody Project project) {
        return ResponseEntity.ok(projectService.updateProject(projectid, project));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable ("id") String projectid) {
        projectService.deleteProject(projectid);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{projectId}/employees")
    public ResponseEntity<List<Employee>> getEmployeesByProjectId(@PathVariable String projectId) {
    List<Employee> employees = projectService.getEmployeesByProjectId(projectId);
    return ResponseEntity.ok(employees);
}

}
