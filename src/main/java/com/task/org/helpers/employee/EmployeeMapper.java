package com.task.org.helpers.employee;

import com.task.org.domain.Department;
import com.task.org.domain.Employee;
import com.task.org.domain.Project;
import com.task.org.dto.employee.EmployeeDTO;
import com.task.org.helpers.organization.OrganizationMapper;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class EmployeeMapper implements Function<Employee, EmployeeDTO> {
    private final OrganizationMapper organizationMapper;

    public EmployeeMapper(OrganizationMapper organizationMapper) {
        this.organizationMapper = organizationMapper;
    }

    @Override
    public EmployeeDTO apply(Employee employee) {
        return new EmployeeDTO(
                employee.getId(),
                employee.getEmployeeName(),
                employee.getSalary(),
                employee.getDepartments().stream().map(Department::getDeptName).collect(Collectors.toSet()),
                organizationMapper.apply(employee.getOrganization()),
                employee.getProjects().stream().map(Project::getProjectName).collect(Collectors.toSet()),
                employee.getUpdatedTimeStamp()
        );
    }
}
