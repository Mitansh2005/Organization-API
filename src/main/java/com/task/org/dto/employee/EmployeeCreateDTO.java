package com.task.org.dto.employee;

import com.task.org.dto.organization.OrganizationCreateDTO;
import com.task.org.dto.department.DepartmentCreateDTO;
import com.task.org.dto.project.ProjectCreateDTO;

import java.time.LocalDateTime;
import java.util.Set;

public class EmployeeCreateDTO {
    private String employeeName;
    private Integer salary;
    private Set<DepartmentCreateDTO> departments;
    private OrganizationCreateDTO organization;
    private Set<ProjectCreateDTO> projects;
    private LocalDateTime createdTimeStamp;

    public Set<DepartmentCreateDTO> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<DepartmentCreateDTO> departments) {
        this.departments = departments;
    }

    public OrganizationCreateDTO getOrganization() {
        return organization;
    }

    public void setOrganizationCreateDTO(OrganizationCreateDTO organization) {
        this.organization = organization;
    }

    public Set<ProjectCreateDTO> getProjects() {
        return projects;
    }

    public void setProjects(Set<ProjectCreateDTO> projects) {
        this.projects = projects;
    }

    public EmployeeCreateDTO(String employeeName, Integer salary, Set<DepartmentCreateDTO> departments, OrganizationCreateDTO organization, Set<ProjectCreateDTO> projects, LocalDateTime createdTimeStamp) {
        this.employeeName = employeeName;
        this.salary = salary;
        this.departments = departments;
        this.organization = organization;
        this.projects = projects;
        this.createdTimeStamp = createdTimeStamp;
    }

    public EmployeeCreateDTO() {
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public LocalDateTime getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    public void setCreatedTimeStamp(LocalDateTime createdTimeStamp) {
        this.createdTimeStamp = createdTimeStamp;
    }
}
