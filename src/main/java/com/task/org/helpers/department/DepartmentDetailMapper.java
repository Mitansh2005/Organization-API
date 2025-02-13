package com.task.org.helpers.department;

import com.task.org.domain.Department;
import com.task.org.dto.department.DepartmentDetailDTO;
import com.task.org.helpers.employee.EmployeeMapper;
import com.task.org.helpers.organization.OrganizationDetailMapper;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DepartmentDetailMapper implements Function<Department, DepartmentDetailDTO> {
    private final EmployeeMapper employeeMapper;
    private final OrganizationDetailMapper organizationDetailMapper;

    public DepartmentDetailMapper(EmployeeMapper employeeMapper, OrganizationDetailMapper organizationDetailMapper) {
        this.employeeMapper = employeeMapper;
        this.organizationDetailMapper = organizationDetailMapper;
    }

    @Override
    public DepartmentDetailDTO apply(Department department) {
        return new DepartmentDetailDTO(
                department.getDeptName(),
                organizationDetailMapper.apply(department.getOrganization()),
                department.getEmployees().stream().map(employeeMapper).collect(Collectors.toSet()),
                department.getUpdatedTimeStamp()
        );
    }
}
