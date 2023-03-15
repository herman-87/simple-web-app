package com.herman87.simplewebapp.controller;

import com.herman87.simplewebapp.domain.Department;
import com.herman87.simplewebapp.error.DepartmentNotFoundException;
import com.herman87.simplewebapp.service.DepartmentService;
import com.herman87.simplewebapp.service.DepartmentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class DepartmentController implements DepartmentService {
    @Autowired
    private DepartmentServiceImpl departmentServiceImpl;
    private final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @Override
    @PostMapping("/department")
    public Department createDepartment(@Valid @RequestBody Department department) {
        logger.info("inside the createDepartment of DepartmentController");
        return departmentServiceImpl.createDepartment(department);
    }

    @Override
    @GetMapping("/departments")
    public List<Department> getAllDepartments() {
        logger.info("inside the getAllDepartment of DepartmentController");
        return departmentServiceImpl.getAllDepartments();
    }

    @Override
    @GetMapping("/department/{id}")
    public Department getDepartmentById(@PathVariable("id") Long departmentId) throws DepartmentNotFoundException {
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
