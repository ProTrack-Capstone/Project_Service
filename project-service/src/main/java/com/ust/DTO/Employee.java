package com.ust.DTO;

public class Employee {
    private String employeeid;
    private String firstName;
    private String lastName;
    private String projectId;
    private String status;
    public Employee() {
    }
    public Employee(String employeeid, String firstName, String lastName, String projectId, String status) {
        this.employeeid = employeeid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.projectId = projectId;
        this.status = status;
    }
    public String getEmployeeid() {
        return employeeid;
    }
    public void setEmployeeid(String employeeid) {
        this.employeeid = employeeid;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
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