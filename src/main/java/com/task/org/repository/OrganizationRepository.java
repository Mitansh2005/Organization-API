package com.task.org.repository;

import com.task.org.domain.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    public Optional<Organization> findByOrgName(String orgName);
}
