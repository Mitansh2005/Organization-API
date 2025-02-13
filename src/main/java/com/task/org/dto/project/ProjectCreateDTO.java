package com.task.org.dto.project;

import com.task.org.dto.organization.OrganizationCreateDTO;

public class ProjectCreateDTO {
    private String projectName;
    private OrganizationCreateDTO organizationCreateDTO;

    public ProjectCreateDTO() {
    }

    public ProjectCreateDTO(String projectName, OrganizationCreateDTO organizationCreateDTO) {
        this.projectName = projectName;
        this.organizationCreateDTO = organizationCreateDTO;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public OrganizationCreateDTO getOrganizationCreateDTO() {
        return organizationCreateDTO;
    }

    public void setOrganizationCreateDTO(OrganizationCreateDTO organizationCreateDTO) {
        this.organizationCreateDTO = organizationCreateDTO;
    }
}
