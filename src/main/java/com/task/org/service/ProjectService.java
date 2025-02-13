package com.task.org.service;

import com.task.org.domain.Employee;
import com.task.org.domain.Organization;
import com.task.org.domain.Project;
import com.task.org.dto.project.ProjectDTO;
import com.task.org.dto.project.ProjectDetailDTO;
import com.task.org.dto.project.ProjectUpdateDTO;
import com.task.org.helpers.project.ProjectDetailMapper;
import com.task.org.helpers.project.ProjectMapper;
import com.task.org.repository.EmployeeRepository;
import com.task.org.repository.OrganizationRepository;
import com.task.org.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private final ProjectDetailMapper projectDetailMapper;
    private final EmployeeRepository employeeRepository;
    private final OrganizationRepository organizationRepository;

    public ProjectService(ProjectRepository projectRepository, ProjectMapper projectMapper,
                          ProjectDetailMapper projectDetailMapper,
                          EmployeeRepository employeeRepository,
                          OrganizationRepository organizationRepository) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
        this.projectDetailMapper = projectDetailMapper;
        this.employeeRepository = employeeRepository;
        this.organizationRepository = organizationRepository;
    }

    public List<ProjectDTO> getAllProjects(Long orgId) {
        return projectRepository.findByOrganizationId(orgId).stream().map(projectMapper).toList();
    }

    public ProjectDetailDTO getProject(Long projectId) {
        return projectRepository.findById(projectId).stream().map(projectDetailMapper).findFirst().orElse(null);
    }

    public void addProject(ProjectDetailDTO projectDetailDTO) {
        Project project = new Project();
        project.setProjectName(projectDetailDTO.getProjectName());

        Organization organization = organizationRepository.findByOrgName(projectDetailDTO.getOrganization().getOrgName()).orElseThrow(() -> new IllegalArgumentException("Organization does not exist: " + projectDetailDTO.getOrganization().getOrgName()));
        project.setOrganization(organization);

        Set<Employee> employees = projectDetailDTO.getEmployees().stream()
                .map(employeeDTO -> employeeRepository.findById(employeeDTO.getId())
                        .orElseThrow(() -> new IllegalStateException("No employee with id: " + employeeDTO.getId() + " found")))
                .collect(Collectors.toSet());
        project.setEmployees(employees);
        for (Employee employee : employees) {
            employee.getProjects().add(project);
        }
        project.setCreatedTimeStamp(LocalDateTime.now());
        project.setUpdatedTimeStamp(LocalDateTime.now());
        projectRepository.save(project);
    }

    public void updateProject(Long projectId, ProjectUpdateDTO projectUpdateDTO) {
        projectRepository.findById(projectId).ifPresent(existingProject -> {
            existingProject.setProjectName(projectUpdateDTO.getProjectName());
            existingProject.setUpdatedTimeStamp(LocalDateTime.now());
            projectRepository.save(existingProject);
        });
    }

    public void addEmployees(Long projectId, List<Long> employeesIds) {
        projectRepository.findById(projectId).ifPresent(existingProject -> {
            Set<Employee> employees = new HashSet<>(employeeRepository.findAllById(employeesIds));
            if (!employees.isEmpty()) {
                existingProject.setEmployees(employees);
                for (Employee employee : employees) {
                    employee.getProjects().add(existingProject);
                }
                projectRepository.save(existingProject);
            } else {
                throw new IllegalStateException("Employee with provided Id does not exist!!");
            }
        });
    }

    public void removeEmployees(Long projectId, List<Long> employeeIds) {
        projectRepository.findById(projectId).ifPresent(existingProject -> {
            employeeRepository.deleteAllById(employeeIds);
        });
    }

    public void deleteProject(Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> {
            throw new IllegalStateException("Project with this id: " + projectId + " not found!");
        });
        project.getEmployees().forEach(employee -> employee.getProjects().remove(project));
        projectRepository.deleteById(projectId);
    }
}
