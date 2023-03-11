package com.herman87.simplewebapp.controller;

import com.herman87.simplewebapp.domain.Department;
import com.herman87.simplewebapp.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class DepartmentController implements DepartmentService {
    private DepartmentService departmentService;
    @Override
    @PostMapping("/department")
    public Department createDepartment(Department department) {
        return departmentService.createDepartment(department);
    }

    @Override
    @GetMapping("/departments")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @Override
    @GetMapping("/department/{id}")
    public Optional<Department> getDepartmentById(@RequestParam("id") Long departmentId) {
        return departmentService.getDepartmentById(departmentId);
    }
}
