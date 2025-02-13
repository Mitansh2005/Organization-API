package com.task.org.repository;

import com.task.org.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    public List<Employee> findByOrganizationId(Long orgId);

    public Set<Employee> findAllByEmployeeNameIn(Set<String> employeeNames);
}
