package com.task.org.dto.organization;


import java.time.LocalDateTime;
import java.util.Set;
public class OrganizationDetailDTO {
    private String orgName;
    private Set<String> deptNames;
    private Set<String> projectNames;
    private Set<String> employeeNames;
    private LocalDateTime updatedTimeStamp;

    public OrganizationDetailDTO(String orgName, Set<String> deptNames, Set<String> projectNames, Set<String> employeeNames, LocalDateTime updatedTimeStamp) {
        this.orgName = orgName;
        this.deptNames = deptNames;
        this.projectNames = projectNames;
        this.employeeNames = employeeNames;
        this.updatedTimeStamp = updatedTimeStamp;
    }

    public OrganizationDetailDTO() {
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Set<String> getDeptNames() {
        return deptNames;
    }

    public void setDeptNames(Set<String> deptNames) {
        this.deptNames = deptNames;
    }

    public Set<String> getProjectNames() {
        return projectNames;
    }

    public void setProjectNames(Set<String> projectNames) {
        this.projectNames = projectNames;
    }

    public Set<String> getEmployeeNames() {
        return employeeNames;
    }

    public void setEmployeeNames(Set<String> employeeNames) {
        this.employeeNames = employeeNames;
    }

    public LocalDateTime getUpdatedTimeStamp() {
        return updatedTimeStamp;
    }

    public void setUpdatedTimeStamp(LocalDateTime updatedTimeStamp) {
        this.updatedTimeStamp = updatedTimeStamp;
    }

}
