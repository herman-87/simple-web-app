package com.herman87.simplewebapp.service;

import com.herman87.simplewebapp.domain.Department;
import com.herman87.simplewebapp.error.DepartmentNotFoundException;
import com.herman87.simplewebapp.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class DepartmentServiceImplTest {
    @Autowired
    private DepartmentServiceImpl objectUnderTest;
    @MockBean
    private DepartmentRepository departmentRepository;
    private Department department1;

    @BeforeEach
    void setup() {
        department1 = mock(Department.class);
    }

    @Test
    @DisplayName("create department successfully")
    void createDepartmentTest() {
        when(departmentRepository.save(department1)).thenReturn(department1);

        assertThat(objectUnderTest.createDepartment(department1))
                .isEqualTo(department1);
    }

    @Test
    @DisplayName("Get all department successfully")
    void getAllDepartmentsTest() {
        final var department2 = mock(Department.class);
        final var department3 = mock(Department.class);

        when(departmentRepository.findAll())
                .thenReturn(List.of(department1, department2, department3));

        assertThat(objectUnderTest.getAllDepartments())
                .containsExactly(department1, department2, department3);
    }

    @Test
    @DisplayName("Get department by id successfully")
    void getDepartmentByIdTest() throws DepartmentNotFoundException {
        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department1));

        assertThat(objectUnderTest.getDepartmentById(1L))
                .isEqualTo(department1);
    }

    @Test
    @DisplayName("Get department by id with a non existing id")
    void getDepartmentByIdFailedTest() {
        when(departmentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> objectUnderTest.getDepartmentById(1L))
                .isInstanceOf(DepartmentNotFoundException.class)
                .hasMessage("Department is available");
    }

    @Test
    @DisplayName("Delete Department by Id successfully")
    void deleteDepartmentByIdTest() {
        objectUnderTest.deleteDepartmentById(1L);
        verify(departmentRepository).deleteById(1L);
    }

    @Test
    @DisplayName("Update Department by Id successfully")
    void updateDepartmentTest() {
        String newCode = "new code";
        String newName = "new name";
        String newAddress = "new address";
        final var newDepartment = Department.builder()
                .name(newName)
                .address(newAddress)
                .code(newCode)
                .build();

        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department1));
        when(departmentRepository.save(department1)).thenReturn(newDepartment);

        assertThat(objectUnderTest.updateDepartment(1L, newDepartment))
                .isEqualTo(newDepartment);

        verify(department1).setName(newName);
        verify(department1).setCode(newCode);
        verify(department1).setAddress(newAddress);
    }

    @Test
    @DisplayName("Get Department by username successfully")
    void getDepartmentByNameTest() throws DepartmentNotFoundException{
        String username = "user name1";
        when(departmentRepository.findDepartmentByNameIgnoreCase(username))
                .thenReturn(Optional.of(department1));

        assertThat(objectUnderTest.getDepartmentByName(username))
                .isEqualTo(department1);
    }

    @Test
    @DisplayName("Get Department by username with non existing username")
    void getDepartmentByNameFailedTest() {
        String username = "user name1";
        when(departmentRepository.findDepartmentByNameIgnoreCase(username))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> objectUnderTest.getDepartmentByName(username))
                .isInstanceOf(DepartmentNotFoundException.class)
                .hasMessage("No Department with the name available");
    }
}