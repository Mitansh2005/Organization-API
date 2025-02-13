package com.task.org.helpers.employee;

import com.task.org.domain.Employee;
import com.task.org.dto.employee.EmployeeGetDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class EmployeeGetMapper implements Function<Employee, EmployeeGetDTO> {
    @Override
    public EmployeeGetDTO apply(Employee employee) {
        return new EmployeeGetDTO(
                employee.getEmployeeName(),
                employee.getSalary(),
                employee.getCreatedTimeStamp(),
                employee.getUpdatedTimeStamp()
        );
    }
}
