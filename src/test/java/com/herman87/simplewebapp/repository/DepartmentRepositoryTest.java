package com.herman87.simplewebapp.repository;

import com.herman87.simplewebapp.domain.Department;
import com.herman87.simplewebapp.repository.DepartmentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class DepartmentRepositoryTest {
    @Autowired
    TestEntityManager testEntityManager;
    String name = "name";
    String code = "code";
    String address = "address";
    @Autowired
    private DepartmentRepository objectUnderTest;

    @Test
    @DisplayName("test get by id method")
    void crudTest() {
        Long departmentId = create();
        findById(departmentId);
        findAll(departmentId);
        update(departmentId);
        delete(departmentId);
    }

    void delete(Long id) {
        objectUnderTest.deleteById(id);
        assertThat(objectUnderTest.findAll())
                .map(Department::getId)
                .doesNotContain(id);
    }

    void update(Long id) {
        Department department = objectUnderTest.findById(id).orElseThrow();
        String newCode = "new code";
        String newName = "new name";
        String newAddress = "new address";
        department.setCode(newCode);
        department.setName(newName);
        department.setAddress(newAddress);

        assertThat(objectUnderTest.save(department))
                .returns(newName, Department::getName)
                .returns(newCode, Department::getCode)
                .returns(newAddress, Department::getAddress);
    }

    void findAll(Long id) {
        Department department = testEntityManager.persist(
                Department.builder().name("name2").build());

        assertThat(objectUnderTest.findAll())
                .hasSize(2)
                .map(Department::getId)
                .contains(id, department.getId());
    }

    Department findById(Long id) {
        Department department = objectUnderTest.findById(id).orElseThrow();
        assertThat(department)
                .returns(name, Department::getName)
                .returns(code, Department::getCode)
                .returns(address, Department::getAddress);

        return department;
    }

    Long create() {
        Department department = Department.builder()
                .name(name)
                .code(code)
                .address(address)
                .build();

        Department savedDepartment = objectUnderTest.save(department);
        Long departmentId = savedDepartment.getId();
        assertThat(departmentId).isNotNull();
        return departmentId;
    }
}
