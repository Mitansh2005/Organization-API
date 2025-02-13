package com.task.org.helpers.project;

import com.task.org.domain.Project;
import com.task.org.dto.project.ProjectUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProjectUpdateMapper implements Function<Project, ProjectUpdateDTO> {
    @Override
    public ProjectUpdateDTO apply(Project project) {
        return new ProjectUpdateDTO(
                project.getProjectName(),
                project.getUpdatedTimeStamp()
        );
    }
}
