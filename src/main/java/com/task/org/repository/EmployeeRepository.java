package com.task.org.repository;

import com.task.org.domain.Department;
import com.task.org.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    public Optional<List<Employee>> findByOrganizationId(Long orgId);
}
