package com.task.org.helpers.project;

import com.task.org.domain.Project;
import com.task.org.dto.project.ProjectDTO;
import com.task.org.helpers.organization.OrganizationMapper;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProjectMapper implements Function<Project, ProjectDTO> {
    private final OrganizationMapper organizationMapper;

    public ProjectMapper(OrganizationMapper organizationMapper) {
        this.organizationMapper = organizationMapper;
    }

    @Override
    public ProjectDTO apply(Project project) {
        return new ProjectDTO(
                project.getProjectName(),
                project.getEmployees().size(),
                organizationMapper.apply(project.getOrganization()),
                project.getUpdatedTimeStamp()
        );
    }
}
