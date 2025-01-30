package com.ust.DTO;

public class Employee {
    private String employeeid;
    private String name;
    private String projectId;
    private String status;
    public Employee() {
    }
    public Employee(String employeeid, String name, String projectId, String status) {
        this.employeeid = employeeid;
        this.name = name;
        this.projectId = projectId;
        this.status = status;
    }
    public String getEmployeeid() {
        return employeeid;
    }
    public void setEmployeeid(String employeeid) {
        this.employeeid = employeeid;
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

}