package com.task.org.dto.department;

import com.task.org.dto.employee.EmployeeDTO;
import com.task.org.dto.organization.OrganizationDetailDTO;
import java.time.LocalDateTime;
import java.util.Set;

public class DepartmentDetailDTO {
    private String deptName;
    private OrganizationDetailDTO organization;
    private Set<EmployeeDTO> employees;
    private LocalDateTime updatedTimeStamp;

    public DepartmentDetailDTO(String deptName, OrganizationDetailDTO organization, Set<EmployeeDTO> employees, LocalDateTime updatedTimeStamp) {
        this.deptName = deptName;
        this.organization = organization;
        this.employees = employees;
        this.updatedTimeStamp = updatedTimeStamp;
    }

    public DepartmentDetailDTO() {
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public OrganizationDetailDTO getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationDetailDTO organization) {
        this.organization = organization;
    }

    public Set<EmployeeDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<EmployeeDTO> employees) {
        this.employees = employees;
    }

    public LocalDateTime getUpdatedTimeStamp() {
        return updatedTimeStamp;
    }

    public void setUpdatedTimeStamp(LocalDateTime updatedTimeStamp) {
        this.updatedTimeStamp = updatedTimeStamp;
    }
}
