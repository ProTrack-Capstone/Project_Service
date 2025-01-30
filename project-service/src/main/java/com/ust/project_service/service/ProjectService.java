package com.ust.project_service.service;

import java.util.List;

import com.ust.DTO.Employee;
import com.ust.project_service.entity.Project;

public interface ProjectService {
    Project addProject(Project project);

    List<Project> getAllProjects();

    Project getProjectById(String projectid);

    Project updateProject(String projectid, Project project);

    void deleteProject(String projectid);

    List<Employee> getEmployeesByProjectId(String projectId);

}
