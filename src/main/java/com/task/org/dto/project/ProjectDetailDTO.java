package com.task.org.dto.project;

import com.task.org.dto.employee.EmployeeDTO;
import com.task.org.dto.organization.OrganizationCreateDTO;

import java.time.LocalDateTime;
import java.util.Set;

public class ProjectDetailDTO {
    private String projectName;
    private Set<EmployeeDTO> employees;
    private OrganizationCreateDTO organization;
    private LocalDateTime updatedTimeStamp;

    public ProjectDetailDTO(String projectName, Set<EmployeeDTO> employees, OrganizationCreateDTO organization, LocalDateTime updatedTimeStamp) {
        this.projectName = projectName;
        this.employees = employees;
        this.organization = organization;
        this.updatedTimeStamp = updatedTimeStamp;
    }

    public ProjectDetailDTO() {
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Set<EmployeeDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<EmployeeDTO> employees) {
        this.employees = employees;
    }

    public OrganizationCreateDTO getOrganization() {
        return this.organization;
    }

    public void setOrganization(OrganizationCreateDTO organization) {
        this.organization = organization;
    }

    public LocalDateTime getUpdatedTimeStamp() {
        return updatedTimeStamp;
    }

    public void setUpdatedTimeStamp(LocalDateTime updatedTimeStamp) {
        this.updatedTimeStamp = updatedTimeStamp;
    }
}
