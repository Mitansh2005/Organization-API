package com.task.org.dto.department;

import com.task.org.dto.organization.OrganizationCreateDTO;

public class DepartmentCreateDTO {
    private String deptName;
    private OrganizationCreateDTO organizationCreateDTO;

    public DepartmentCreateDTO() {
    }

    public DepartmentCreateDTO(String deptName, OrganizationCreateDTO organizationCreateDTO) {
        this.deptName = deptName;
        this.organizationCreateDTO = organizationCreateDTO;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public OrganizationCreateDTO getOrganizationCreateDTO() {
        return organizationCreateDTO;
    }

    public void setOrganizationCreateDTO(OrganizationCreateDTO organizationCreateDTO) {
        this.organizationCreateDTO = organizationCreateDTO;
    }
}
