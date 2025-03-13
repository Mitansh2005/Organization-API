package com.task.org.repository;

import com.task.org.domain.Department;
import com.task.org.domain.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    public Optional<List<Department>> findByOrganizationId(Long orgId);

    Set<Department> findByDeptNameAndOrganization(String deptName, Organization organization);


}
