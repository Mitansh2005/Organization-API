package com.task.org.helpers.organization;

import com.task.org.domain.Organization;
import com.task.org.dto.organization.OrganizationCreateDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class OrganizationCreateMapper implements Function<Organization, OrganizationCreateDTO> {

    @Override
    public OrganizationCreateDTO apply(Organization organization) {
        return new OrganizationCreateDTO(
                organization.getOrgName()
        );
    }
}
