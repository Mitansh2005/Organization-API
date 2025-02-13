package com.task.org.helpers.organization;

import com.task.org.domain.Organization;
import com.task.org.dto.organization.OrganizationDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class OrganizationMapper implements Function<Organization, OrganizationDTO> {
    @Override
    public OrganizationDTO apply(Organization organization) {
        return new OrganizationDTO(
                organization.getOrgName(),
                organization.getDepartments() != null ? organization.getDepartments().size() : 0,
                organization.getProjects() != null ? organization.getProjects().size() : 0,
                organization.getEmployees() != null ? organization.getEmployees().size() : 0,
                organization.getUpdatedTimeStamp()
        );
    }
}
