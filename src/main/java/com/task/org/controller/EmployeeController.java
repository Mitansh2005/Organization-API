package com.task.org.controller;

import com.task.org.dto.employee.*;
import com.task.org.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/organization/{orgId}")
    public ResponseEntity<List<EmployeeGetDTO>> getAllEmployeesByOrg(@PathVariable Long orgId) {
        return new ResponseEntity<>(employeeService.getEmployeesByOrganization(orgId), HttpStatus.OK);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long employeeId) {
        return new ResponseEntity<>(employeeService.getEmployeesById(employeeId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addEmployee(@RequestBody EmployeeCreateDTO employeeDTO) {
        employeeService.addEmployee(employeeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<Void> updateEmployee(@PathVariable Long employeeId, @RequestBody EmployeeUpdateDTO employeeUpdateDTO) {
        employeeService.updateEmployee(employeeId, employeeUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/{employeeId}/department")
    public ResponseEntity<Void> updateDepartmentOfEmployee(@PathVariable Long employeeId, @RequestBody EmployeeUpdateDeptDTO employeeUpdateDeptDTO) {
        employeeService.updateDepartmentOfEmployee(employeeId, employeeUpdateDeptDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
