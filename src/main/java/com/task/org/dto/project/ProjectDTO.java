package com.task.org.dto.project;

import com.task.org.dto.organization.OrganizationDTO;

import java.time.LocalDateTime;

public class ProjectDTO {
    private String projectName;
    private int numOfEmployees;
    private OrganizationDTO organization;
    private LocalDateTime updateTimeStamp;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getNumOfEmployees() {
        return numOfEmployees;
    }

    public void setNumOfEmployees(int numOfEmployees) {
        this.numOfEmployees = numOfEmployees;
    }

    public OrganizationDTO getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationDTO organization) {
        this.organization = organization;
    }

    public LocalDateTime getUpdateTimeStamp() {
        return updateTimeStamp;
    }

    public void setUpdateTimeStamp(LocalDateTime updateTimeStamp) {
        this.updateTimeStamp = updateTimeStamp;
    }

    public ProjectDTO() {
    }

    public ProjectDTO(String projectName, int numOfEmployees, OrganizationDTO organization, LocalDateTime updateTimeStamp) {
        this.projectName = projectName;
        this.numOfEmployees = numOfEmployees;
        this.organization = organization;
        this.updateTimeStamp = updateTimeStamp;
    }
}
