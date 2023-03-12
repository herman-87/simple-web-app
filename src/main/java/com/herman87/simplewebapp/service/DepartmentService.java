package com.herman87.simplewebapp.service;

import com.herman87.simplewebapp.domain.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    Department createDepartment(Department department);
    List<Department> getAllDepartments();
    Optional<Department> getDepartmentById(Long departmentId);
    void deleteDepartmentById(Long id);
    Department updateDepartment(Long id, Department department);

}
