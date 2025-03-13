package com.task.org.repository;

import com.task.org.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    public Optional<List<Employee>> findByOrganizationId(Long orgId);
}
