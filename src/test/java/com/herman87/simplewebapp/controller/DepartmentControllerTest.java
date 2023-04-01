package com.herman87.simplewebapp.controller;

import com.herman87.simplewebapp.domain.Department;
import com.herman87.simplewebapp.service.DepartmentServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {
    Department department1 = Department
            .builder()
            .name("name")
            .code("code")
            .address("address")
            .build();
    Department department2 = Department
            .builder()
            .id(1L)
            .name("name")
            .code("code")
            .address("address")
            .build();
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DepartmentServiceImpl departmentServiceImpl;

    @Test
    void createDepartment() throws Exception {
        when(departmentServiceImpl.createDepartment(department1))
                .thenReturn(department2);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/department")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(" {\n" +
                                        "    \"name\":\"name\",\n" +
                                        "    \"code\":\"code\",\n" +
                                        "    \"address\": \"address\"\n" +
                                        "    }"))
                .andExpect(status().isOk());

    }

    @Test
    void getAllDepartments() {
    }

    @Test
    void getDepartmentById() throws Exception {
        when(departmentServiceImpl.getDepartmentById(1L))
                .thenReturn(department1);

        mockMvc.perform(MockMvcRequestBuilders.get("/department/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(department1.getName()));
    }

    @Test
    void deleteDepartmentById() {
    }

    @Test
    void updateDepartment() {
    }

    @Test
    void getDepartmentByName() {
    }
}