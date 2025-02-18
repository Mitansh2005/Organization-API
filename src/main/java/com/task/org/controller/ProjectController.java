package com.task.org.controller;

import com.task.org.dto.project.ProjectDTO;
import com.task.org.dto.project.ProjectDetailDTO;
import com.task.org.dto.project.ProjectUpdateDTO;
import com.task.org.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/organization/{orgId}")
    public ResponseEntity<List<ProjectDTO>> getAllProjects(@PathVariable Long orgId) {
        return new ResponseEntity<>(projectService.getAllProjects(orgId), HttpStatus.OK);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectDetailDTO> getProjectById(@PathVariable Long projectId) {
        return new ResponseEntity<>(projectService.getProject(projectId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addProject(@RequestBody ProjectDetailDTO projectDetailDTO) {
        projectService.addProject(projectDetailDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<Void> updateProject(@PathVariable Long projectId, @RequestBody ProjectUpdateDTO projectUpdateDTO) {
        projectService.updateProject(projectId, projectUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/{projectId}/employees/add")
    public ResponseEntity<Void> addEmployeesInProject(@PathVariable Long projectId, @RequestBody List<Long> employeeIds) {
        projectService.addEmployees(projectId, employeeIds);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/{projectId}/employees/remove")
    public ResponseEntity<Void> removeEmployeesFromProject(@PathVariable Long projectId, @RequestBody List<Long> employeeIds) {
        projectService.removeEmployees(projectId, employeeIds);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long projectId) {
        projectService.deleteProject(projectId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
