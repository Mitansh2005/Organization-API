package com.task.org.service;

import com.task.org.domain.Department;
import com.task.org.domain.Employee;
import com.task.org.dto.department.DepartmentDTO;
import com.task.org.dto.department.DepartmentDetailDTO;
import com.task.org.helpers.department.DepartmentDetailMapper;
import com.task.org.helpers.department.DepartmentMapper;
import com.task.org.repository.DepartmentRepository;
import com.task.org.repository.EmployeeRepository;
import com.task.org.repository.OrganizationRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;
    private final DepartmentDetailMapper departmentDetailMapper;
    private final OrganizationRepository organizationRepository;
    private final EmployeeRepository employeeRepository;

    public DepartmentService(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper, DepartmentDetailMapper departmentDetailMapper,
                             OrganizationRepository organizationRepository,
                             EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
        this.departmentDetailMapper = departmentDetailMapper;
        this.organizationRepository = organizationRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<DepartmentDTO> getAllDepartmentsByOrganization(Long orgId) {
        return departmentRepository.findByOrganizationId(orgId).stream().map(departmentMapper).collect(Collectors.toList());
    }

    public DepartmentDetailDTO getDepartment(Long deptId) {
        return departmentRepository.findById(deptId).stream().map(departmentDetailMapper).findFirst().orElse(null);
    }

    public void addDepartment(DepartmentDTO departmentDTO) {
        Department department = new Department();
        department.setDeptName(departmentDTO.getDeptName());
        department.setOrganization(organizationRepository.findByOrgName(departmentDTO.getOrganization().getOrgName()).orElse(null));
        department.setCreatedTimeStamp(LocalDateTime.now());
        department.setUpdatedTimeStamp(LocalDateTime.now());
        departmentRepository.save(department);
    }

    public void updateDepartment(Long deptId, DepartmentDTO departmentDTO) {
        departmentRepository.findById(deptId).ifPresent(existingDepartment -> {
            existingDepartment.setDeptName(departmentDTO.getDeptName());
            existingDepartment.setOrganization(organizationRepository.findByOrgName(departmentDTO.getOrganization().getOrgName()).orElse(null));
            existingDepartment.setUpdatedTimeStamp(LocalDateTime.now());
            departmentRepository.save(existingDepartment);
        });
    }

    public void removeEmployees(Long deptId, List<Long> employeesId) {
        departmentRepository.findById(deptId).ifPresent(existingDepartment -> {
            departmentRepository.deleteByEmployeesIdIn(employeesId);
        });
    }

    public void addEmployees(Long deptId, List<Long> employeeIds) {
        departmentRepository.findById(deptId).ifPresent(existingDepartment -> {
            Set<Employee> employees = new HashSet<>(employeeRepository.findAllById(employeeIds));
            if(!employees.isEmpty()) {
                existingDepartment.setEmployees(employees);
                for (Employee employee: employees){
                    employee.getDepartments().add(existingDepartment);
                }
                departmentRepository.save(existingDepartment);
            } else {
                throw new IllegalStateException("Employee with provided Id does not exist!!");
            }
        });
    }

    public void deleteDepartment(Long deptId) {
        Department department= departmentRepository.findById(deptId).orElseThrow(()->{
            throw new IllegalStateException("No Department with id: "+deptId+" was found");
        });
        department.getEmployees().forEach(employee -> employee.getDepartments().remove(department));
        departmentRepository.deleteById(deptId);

    }

}
