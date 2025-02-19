package com.task.org.service;

import com.task.org.domain.Department;
import com.task.org.domain.Employee;
import com.task.org.domain.Organization;
import com.task.org.dto.employee.*;
import com.task.org.helpers.employee.EmployeeGetMapper;
import com.task.org.helpers.employee.EmployeeMapper;
import com.task.org.repository.DepartmentRepository;
import com.task.org.repository.EmployeeRepository;
import com.task.org.repository.OrganizationRepository;
import com.task.org.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final EmployeeGetMapper employeeGetMapper;
    private final DepartmentRepository departmentRepository;
    private final ProjectRepository projectRepository;
    private final OrganizationRepository organizationRepository;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper,
                           EmployeeGetMapper employeeGetMapper,
                           DepartmentRepository departmentRepository,
                           ProjectRepository projectRepository,
                           OrganizationRepository organizationRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.employeeGetMapper = employeeGetMapper;
        this.departmentRepository = departmentRepository;
        this.projectRepository = projectRepository;
        this.organizationRepository = organizationRepository;
    }

    public List<EmployeeGetDTO> getEmployeesByOrganization(Long orgId) {
        List<Employee> employees = employeeRepository.findByOrganizationId(orgId).orElseThrow(()->{
            throw new IllegalStateException("No organization with id: "+orgId);
        });
        return employees.stream().map(employeeGetMapper).collect(Collectors.toList());
    }

    public EmployeeDTO getEmployeesById(Long employeeId) {
        return employeeRepository.findById(employeeId).map(employeeMapper).orElseThrow(()->{
            throw new IllegalStateException("No employee by id: "+ employeeId);
        });
    }

    public void addEmployee(EmployeeCreateDTO employeeDTO) {
        Employee employee = new Employee();
        Organization organization = organizationRepository.findByOrgName(employeeDTO.getOrganization().getOrgName()).orElseThrow(() -> {
            throw new IllegalStateException("Organization does not exists!");
        });
        employee.setEmployeeName(employeeDTO.getEmployeeName());
        employee.setSalary(employeeDTO.getSalary());
        employee.setDepartments(employeeDTO.getDepartments().stream()
                .flatMap(departmentDTO -> departmentRepository
                        .findByDeptNameAndOrganization(departmentDTO.getDeptName(), organization)
                        .stream())
                .collect(Collectors.toSet()));
        if (employeeDTO.getProjects() != null) {
            employee.setProjects(employeeDTO.getProjects().stream().map(project -> projectRepository.findByProjectName(project.getProjectName()).orElseThrow(()->{
                throw new IllegalStateException("No project by name: "+project.getProjectName());
            })).collect(Collectors.toSet()));
        }
        employee.setOrganization(organizationRepository.findByOrgName(employeeDTO.getOrganization().getOrgName()).orElseThrow(() -> {
            throw new IllegalStateException("Organization does not exist!");
        }));
        employee.setCreatedTimeStamp(LocalDateTime.now());
        employee.setUpdatedTimeStamp(LocalDateTime.now());
        employeeRepository.save(employee);
    }

    public void updateEmployee(Long employeeId, EmployeeUpdateDTO employeeUpdateDTO) {
        employeeRepository.findById(employeeId).ifPresentOrElse(existingEmployee -> {
            existingEmployee.setEmployeeName(employeeUpdateDTO.getEmployeeName());
            existingEmployee.setSalary(employeeUpdateDTO.getSalary());
            existingEmployee.setUpdatedTimeStamp(LocalDateTime.now());
            employeeRepository.save(existingEmployee);
        },()->{
            throw new IllegalStateException("No employee by id: "+employeeId);
        });
    }

    public void updateDepartmentOfEmployee(Long employeeId, EmployeeUpdateDeptDTO employeeUpdateDeptDTO) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> {
            throw new IllegalStateException("No employee with Id: " + employeeId);
        });
        List<Department> departments = departmentRepository.findAllById(employeeUpdateDeptDTO.getDeptIds());
        if (departments.isEmpty()) {
            throw new NullPointerException("No departments found");
        } else {
            employee.setDepartments(new HashSet<>(departments));
            employeeRepository.save(employee);
        }
    }

    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->{
            throw new IllegalStateException("No employee with id: "+employeeId);
        });
        employeeRepository.deleteById(employeeId);
    }
}
