package com.ust.project_service.service.impl;


import com.ust.project_service.client.EmployeeClient;
import com.ust.project_service.entity.Project;
import com.ust.project_service.repository.ProjectRepository;
import com.ust.project_service.service.ProjectService;

import org.springframework.stereotype.Service;

import java.util.List;

import com.ust.DTO.Employee;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final EmployeeClient employeeClient;

    public ProjectServiceImpl(ProjectRepository projectRepository, EmployeeClient employeeClient) {
        this.projectRepository = projectRepository;
        this.employeeClient = employeeClient;
    }

    @Override
    public Project addProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project getProjectById(String projectid) {
        return projectRepository.findById(projectid)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + projectid));
    }

    @Override
    public Project updateProject(String projectid, Project project) {
        Project existingProject = getProjectById(projectid);
        existingProject.setName(project.getName());
        existingProject.setClientName(project.getClientName());
        existingProject.setBudget(project.getBudget());
        existingProject.setStartDate(project.getStartDate());
        existingProject.setEndDate(project.getEndDate());
        existingProject.setStatus(project.getStatus());
        return projectRepository.save(existingProject);
    }

    @Override
    public void deleteProject(String projectid) {
        projectRepository.deleteById(projectid);
    }

    @Override
    public List<Employee> getEmployeesByProjectId(String projectId) {
        return employeeClient.getEmployeesByProjectId(projectId);
    }
}
