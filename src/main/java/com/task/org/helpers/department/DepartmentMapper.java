package com.task.org.helpers.department;

import com.task.org.domain.Department;
import com.task.org.dto.department.DepartmentDTO;
import com.task.org.helpers.organization.OrganizationCreateMapper;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class DepartmentMapper implements Function<Department, DepartmentDTO> {
    private final OrganizationCreateMapper organizationCreateMapper;

    public DepartmentMapper(OrganizationCreateMapper organizationCreateMapper) {
        this.organizationCreateMapper = organizationCreateMapper;
    }

    @Override
    public DepartmentDTO apply(Department department) {
        return new DepartmentDTO(
                department.getDeptName(),
                organizationCreateMapper.apply(department.getOrganization()),
                department.getEmployees().size(),
                department.getUpdatedTimeStamp()
        );
    }
}
