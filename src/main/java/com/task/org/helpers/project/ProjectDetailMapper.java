package com.task.org.helpers.project;

import com.task.org.domain.Project;
import com.task.org.dto.project.ProjectDetailDTO;
import com.task.org.helpers.employee.EmployeeMapper;
import com.task.org.helpers.organization.OrganizationCreateMapper;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ProjectDetailMapper implements Function<Project,ProjectDetailDTO> {
    private final EmployeeMapper employeeMapper;
    private final OrganizationCreateMapper organizationCreateMapper;

    public ProjectDetailMapper(EmployeeMapper employeeMapper, OrganizationCreateMapper organizationCreateMapper) {
        this.employeeMapper = employeeMapper;
        this.organizationCreateMapper = organizationCreateMapper;
    }

    @Override
    public ProjectDetailDTO apply(Project project) {
        return new ProjectDetailDTO(
                project.getProjectName(),
                project.getEmployees().stream().map(employeeMapper).collect(Collectors.toSet()),
                organizationCreateMapper.apply(project.getOrganization()),
                project.getUpdatedTimeStamp()
        );
    }
}
