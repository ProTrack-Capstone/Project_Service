package com.ust.DTO;

public class Employee {
    private String employeeId;
    private String name;
    private String projectId;
    private String status;
    public Employee() {
    }
    public Employee(String employeeId, String name, String projectId, String status) {
        this.employeeId = employeeId;
        this.name = name;
        this.projectId = projectId;
        this.status = status;
    }
    public String getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getProjectId() {
        return projectId;
    }
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    // Constructors, Getters, and Setters
    
}
