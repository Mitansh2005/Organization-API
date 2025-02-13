package com.task.org.dto.department;


import com.task.org.dto.organization.OrganizationCreateDTO;
import jakarta.annotation.Nullable;

import java.time.LocalDateTime;

public class DepartmentDTO {
    private String deptName;
    private OrganizationCreateDTO organization;
    @Nullable
    private int numOfEmployees;
    private LocalDateTime updatedTimeStamp;

    public DepartmentDTO(String deptName, OrganizationCreateDTO organization, int numOfEmployees, LocalDateTime updatedTimeStamp) {
        this.deptName = deptName;
        this.organization = organization;
        this.numOfEmployees = numOfEmployees;
        this.updatedTimeStamp = updatedTimeStamp;
    }

    public DepartmentDTO() {
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public OrganizationCreateDTO getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationCreateDTO organization) {
        this.organization = organization;
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
