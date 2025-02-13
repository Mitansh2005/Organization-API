package com.task.org.dto.employee;


import java.util.List;

public class EmployeeUpdateDeptDTO {
    private List<Long> deptIds;

    public EmployeeUpdateDeptDTO(List<Long> deptIds) {
        this.deptIds = deptIds;
    }

    public EmployeeUpdateDeptDTO() {
    }

    public List<Long> getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(List<Long> deptIds) {
        this.deptIds = deptIds;
    }
}
