package com.herman87.simplewebapp.service;

import com.herman87.simplewebapp.domain.Department;
import com.herman87.simplewebapp.error.DepartmentNotFoundException;

import java.util.List;

public interface DepartmentService {
    Department createDepartment(Department department);
    List<Department> getAllDepartments();
    Department getDepartmentById(Long departmentId) throws DepartmentNotFoundException;
    void deleteDepartmentById(Long id);
    Department updateDepartment(Long id, Department department);
    Department getDepartmentByName(String name) throws DepartmentNotFoundException;

}
