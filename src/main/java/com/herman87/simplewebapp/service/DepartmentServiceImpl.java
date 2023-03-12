package com.herman87.simplewebapp.service;

import com.herman87.simplewebapp.domain.Department;
import com.herman87.simplewebapp.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService{
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Optional<Department> getDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId);
    }

    @Override
    public void deleteDepartmentById(Long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public Department updateDepartment(Long id, Department department) {
        Department departmentToUpdate = departmentRepository.findById(id).orElseThrow();

        String address = department.getAddress();
        String name = department.getAddress();
        String code = department.getAddress();

        if (Objects.nonNull(address)) {
            departmentToUpdate.setAddress(address);
        }
        if (Objects.nonNull(name)) {
            departmentToUpdate.setName(name);
        }
        if (Objects.nonNull(code)) {
            departmentToUpdate.setCode(code);
        }
        return departmentRepository.save(departmentToUpdate);
    }
}
