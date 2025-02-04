package com.ust.project_service.service.impl;


import java.time.LocalDate;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ust.DTO.Employee;
import com.ust.project_service.client.EmployeeClient;
import com.ust.project_service.entity.Project;
import com.ust.project_service.repository.ProjectRepository;
import com.ust.project_service.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Override
    public void updateEmployeesStatus(List<String> employeeIds, String projectId) {
        // Implement the logic to update employee status
        employeeClient.updateEmployeesStatus(employeeIds, projectId);
    }
    private final ProjectRepository projectRepository;
    private final EmployeeClient employeeClient;

    public ProjectServiceImpl(ProjectRepository projectRepository, EmployeeClient employeeClient) {
        this.projectRepository = projectRepository;
        this.employeeClient = employeeClient;
    }

    @Override
    public Project addProject(Project project) {
        // Set the initial status to ACTIVE
        project.setStatus("ACTIVE");

        // Save the project to the repository
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
        existingProject.setNumberOfEmployees(project.getNumberOfEmployees());
        existingProject.setProjectDomain(project.getProjectDomain());
        existingProject.setNumberOfEmployees(project.getNumberOfEmployees());
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

    @Override
    public List<Employee> getUnassignedEmployees() {
        // Implement the logic to get unassigned employees
        return employeeClient.getUnassignedEmployees();
    }

    @Override
    public void assignEmployees(String projectId, List<String> employeeIds) {
        // Fetch the project by ID
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    
        // Call the Employee Microservice to update employee status
        employeeClient.updateEmployeesStatus(employeeIds, projectId);
    
        // Fetch updated list of assigned employees
        List<Employee> assignedEmployees = employeeClient.getEmployeesByProjectId(projectId);
    
        // Check if the required number of employees are assigned
        if (assignedEmployees.size() >= project.getNumberOfEmployees()) {
            project.setStatus("IN PROGRESS");
        }
    
        // Save the project with updated status
        projectRepository.save(project);
    }
    


    // @Scheduled(fixedRate = 10000) 
    // public void updateCompletedProjects() {
    //     List<Project> projects = projectRepository.findAll();

    //     for (Project project : projects) {
    //         if (project.getEndDate().isBefore(LocalDate.now())) {
    //             project.setStatus("COMPLETED");
    //             projectRepository.save(project);
    //         }
    //     }

    @Scheduled(fixedRate = 10000) 
    public void updateCompletedProjects() {
        List<Project> projects = projectRepository.findAll();
        LocalDate today = LocalDate.now();

        for (Project project : projects) {
            // Check if the project is "IN PROGRESS" and the end date has passed
            if ("IN PROGRESS".equals(project.getStatus()) && project.getEndDate().isBefore(today)) {
                project.setStatus("COMPLETED");
                projectRepository.save(project);
            }
        }
    }
}
