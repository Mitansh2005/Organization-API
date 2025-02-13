package com.task.org.dto.employee;


import java.time.LocalDateTime;

public class EmployeeGetDTO {
    private String employeeName;
    private Integer salary;
    private LocalDateTime createdTimeStamp;
    private LocalDateTime updatedTimeStamp;

    public EmployeeGetDTO() {
    }

    public EmployeeGetDTO(String employeeName, Integer salary, LocalDateTime createdTimeStamp, LocalDateTime updatedTimeStamp) {
        this.employeeName = employeeName;
        this.salary = salary;
        this.createdTimeStamp = createdTimeStamp;
        this.updatedTimeStamp = updatedTimeStamp;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
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
}
