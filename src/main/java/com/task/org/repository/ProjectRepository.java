package com.task.org.repository;

import com.task.org.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    public List<Project> findByOrganizationId(Long orgId);

    public Optional<Project> findByProjectName(String name);
}
