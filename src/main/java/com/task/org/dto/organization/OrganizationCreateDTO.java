package com.task.org.dto.organization;


public class OrganizationCreateDTO {
    private String orgName;

    public OrganizationCreateDTO() {
    }

    public OrganizationCreateDTO(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

}
