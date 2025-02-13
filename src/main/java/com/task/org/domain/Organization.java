package com.task.org.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "organizations")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orgName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organization")
    private Set<Department> departments = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organization")
    private Set<Project> projects = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organization")
    private Set<Employee> employees = new HashSet<>();

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdTimeStamp;

    @Column(nullable = false)
    private LocalDateTime updatedTimeStamp;

    public Organization() {
    }

    public Organization(Long id, String orgName, Set<Department> departments, Set<Project> projects, Set<Employee> employees, LocalDateTime createdTimeStamp, LocalDateTime updatedTimeStamp) {
        this.id = id;
        this.orgName = orgName;
        this.departments = departments;
        this.projects = projects;
        this.employees = employees;
        this.createdTimeStamp = createdTimeStamp;
        this.updatedTimeStamp = updatedTimeStamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public LocalDateTime getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    public void setCreatedTimeStamp(LocalDateTime createdTimeStamp) {
        this.createdTimeStamp = createdTimeStamp;
    }

    public LocalDateTime getUpdatedTimeStamp() {
        return updatedTimeStamp;
    }

    public void setUpdatedTimeStamp(LocalDateTime updatedTimeStamp) {
        this.updatedTimeStamp = updatedTimeStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Organization that)) return false;
        return getOrgName().equals(that.getOrgName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrgName());
    }
}
