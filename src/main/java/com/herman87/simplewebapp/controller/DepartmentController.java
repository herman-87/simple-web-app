package com.herman87.simplewebapp.controller;

import com.herman87.simplewebapp.domain.Department;
import com.herman87.simplewebapp.service.DepartmentService;
import com.herman87.simplewebapp.service.DepartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class DepartmentController implements DepartmentService {
    @Autowired
    private DepartmentServiceImpl departmentServiceImpl;

    @Override
    @PostMapping("/department")
    public Department createDepartment(@Valid @RequestBody Department department) {
        return departmentServiceImpl.createDepartment(department);
    }

    @Override
    @GetMapping("/departments")
    public List<Department> getAllDepartments() {
        return departmentServiceImpl.getAllDepartments();
    }

    @Override
    @GetMapping("/department/{id}")
    public Optional<Department> getDepartmentById(@PathVariable("id") Long departmentId) {
        return departmentServiceImpl.getDepartmentById(departmentId);
    }

    @Override
    @DeleteMapping("/department/{id}")
    public void deleteDepartmentById(@PathVariable Long id) {
        departmentServiceImpl.deleteDepartmentById(id);
    }

    @Override
    @PutMapping("/department/{id}")
    public Department updateDepartment(
            @PathVariable Long id,
            @RequestBody Department department
    ) {
        return departmentServiceImpl.updateDepartment(id, department);
    }
}
