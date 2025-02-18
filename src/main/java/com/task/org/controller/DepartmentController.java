package com.task.org.controller;

import com.task.org.dto.department.DepartmentDTO;
import com.task.org.dto.department.DepartmentDetailDTO;
import com.task.org.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/organization/{orgId}")
    public ResponseEntity<List<DepartmentDTO>> getDepartmentsByOrganization(@PathVariable Long orgId) {
        return new ResponseEntity<>(departmentService.getAllDepartmentsByOrganization(orgId), HttpStatus.OK);
    }

    @GetMapping("/{deptId}")
    public ResponseEntity<DepartmentDetailDTO> getDepartmentById(@PathVariable Long deptId) {
        return new ResponseEntity<>(departmentService.getDepartment(deptId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addDepartment(@RequestBody DepartmentDTO departmentDTO) {
        departmentService.addDepartment(departmentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{deptId}")
    public ResponseEntity<Void> updateDepartment(@PathVariable Long deptId, @RequestBody DepartmentDTO departmentDTO) {
        departmentService.updateDepartment(deptId, departmentDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/{deptId}/employees/remove")
    public ResponseEntity<Void> removeEmployeeFromDept(@PathVariable Long deptId, @RequestBody List<Long> employeeIds) {
        departmentService.removeEmployees(deptId, employeeIds);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/{deptId}/employees/add")
    public ResponseEntity<Void> addEmployeeToDept(@PathVariable Long deptId, @RequestBody List<Long> employeeIds) {
        departmentService.addEmployees(deptId, employeeIds);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{deptId}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long deptId) {
        departmentService.deleteDepartment(deptId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
