package com.task.org.dto.project;


import java.time.LocalDateTime;

public class ProjectUpdateDTO {
    private String projectName;
    private LocalDateTime updatedTimeStamp;

    public ProjectUpdateDTO() {
    }

    public ProjectUpdateDTO(String projectName, LocalDateTime updatedTimeStamp) {
        this.projectName = projectName;
        this.updatedTimeStamp = updatedTimeStamp;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public LocalDateTime getUpdatedTimeStamp() {
        return updatedTimeStamp;
    }

    public void setUpdatedTimeStamp(LocalDateTime updatedTimeStamp) {
        this.updatedTimeStamp = updatedTimeStamp;
    }
}
