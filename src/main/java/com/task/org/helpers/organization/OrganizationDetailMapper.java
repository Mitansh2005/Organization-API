package com.task.org.helpers.organization;

import com.task.org.domain.Department;
import com.task.org.domain.Employee;
import com.task.org.domain.Organization;
import com.task.org.domain.Project;
import com.task.org.dto.organization.OrganizationDetailDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrganizationDetailMapper implements Function<Organization, OrganizationDetailDTO> {

    @Override
    public OrganizationDetailDTO apply(Organization organization) {
        return new OrganizationDetailDTO(
                organization.getOrgName(),
                organization.getDepartments().stream().map(Department::getDeptName).collect(Collectors.toSet()),
                organization.getProjects().stream().map(Project::getProjectName).collect(Collectors.toSet()),
                organization.getEmployees().stream().map(Employee::getEmployeeName).collect(Collectors.toSet()),
                organization.getUpdatedTimeStamp()
        );
    }
}
