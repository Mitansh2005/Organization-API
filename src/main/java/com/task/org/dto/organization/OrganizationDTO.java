package com.task.org.dto.organization;


import java.time.LocalDateTime;
public class OrganizationDTO {
    private String orgName;
    private int numOfDepartments;
    private int numOfProjects;
    private int numOfEmployees;
    private LocalDateTime updatedTimeStamp;

    public OrganizationDTO(String orgName, int numOfDepartments, int numOfProjects, int numOfEmployees, LocalDateTime updatedTimeStamp) {
        this.orgName = orgName;
        this.numOfDepartments = numOfDepartments;
        this.numOfProjects = numOfProjects;
        this.numOfEmployees = numOfEmployees;
        this.updatedTimeStamp = updatedTimeStamp;
    }

    public OrganizationDTO() {
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public int getNumOfDepartments() {
        return numOfDepartments;
    }

    public void setNumOfDepartments(int numOfDepartments) {
        this.numOfDepartments = numOfDepartments;
    }

    public int getNumOfProjects() {
        return numOfProjects;
    }

    public void setNumOfProjects(int numOfProjects) {
        this.numOfProjects = numOfProjects;
    }

    public int getNumOfEmployees() {
        return numOfEmployees;
    }

    public void setNumOfEmployees(int numOfEmployees) {
        this.numOfEmployees = numOfEmployees;
    }

    public LocalDateTime getUpdatedTimeStamp() {
        return updatedTimeStamp;
    }

    public void setUpdatedTimeStamp(LocalDateTime updatedTimeStamp) {
        this.updatedTimeStamp = updatedTimeStamp;
    }
}
