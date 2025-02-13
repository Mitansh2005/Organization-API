package com.task.org.dto.employee;


import java.time.LocalDateTime;

public class EmployeeUpdateDTO {
    private Long id;
    private String employeeName;
    private Integer salary;
    private LocalDateTime updatedTimeStamp;

    public EmployeeUpdateDTO() {
    }

    public EmployeeUpdateDTO(Long id, String employeeName, Integer salary, LocalDateTime updatedTimeStamp) {
        this.id = id;
        this.employeeName = employeeName;
        this.salary = salary;
        this.updatedTimeStamp = updatedTimeStamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getUpdatedTimeStamp() {
        return updatedTimeStamp;
    }

}
