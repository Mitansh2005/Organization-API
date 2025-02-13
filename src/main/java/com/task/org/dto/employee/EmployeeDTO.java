package com.task.org.dto.employee;

import com.task.org.dto.organization.OrganizationDTO;
import jakarta.annotation.Nullable;

import java.time.LocalDateTime;
import java.util.Set;

public class EmployeeDTO {

    private Long id;
    @Nullable
    private String employeeName;
    @Nullable
    private Integer salary;
    @Nullable
    private Set<String> deptNames;
    @Nullable
    private OrganizationDTO organization;
    private Set<String> projectNames;
    private LocalDateTime updatedTimeStamp;

    public EmployeeDTO(Long id, String employeeName, Integer salary, Set<String> deptNames, OrganizationDTO organization, Set<String> projectNames, LocalDateTime updatedTimeStamp) {
        this.id = id;
        this.employeeName = employeeName;
        this.salary = salary;
        this.deptNames = deptNames;
        this.organization = organization;
        this.projectNames = projectNames;
        this.updatedTimeStamp = updatedTimeStamp;
    }

    public EmployeeDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<String> getDeptNames() {
        return deptNames;
    }

    public void setDeptNames(Set<String> deptNames) {
        this.deptNames = deptNames;
    }

    public OrganizationDTO getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationDTO organization) {
        this.organization = organization;
    }

    public Set<String> getProjectNames() {
        return projectNames;
    }

    public void setProjectNames(Set<String> projectNames) {
        this.projectNames = projectNames;
    }

    public LocalDateTime getUpdatedTimeStamp() {
        return updatedTimeStamp;
    }

    public void setUpdatedTimeStamp(LocalDateTime updatedTimeStamp) {
        this.updatedTimeStamp = updatedTimeStamp;
    }
}
