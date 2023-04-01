package com.herman87.simplewebapp.repository;

import com.herman87.simplewebapp.domain.Department;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class DepartmentRepositoryTest {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @DisplayName("Test find by Id method")
    void findByIdTest() {
        final var department = testEntityManager.persist(
                Department.builder()
                        .code("department code")
                        .name("department name")
                        .address("department address")
                        .build()
        );

        assertThat(departmentRepository.findById(department.getId()).orElseThrow())
                .isEqualTo(department);
    }

    @Test
    @DisplayName("Test save Department")
    void saveDepartmentTest() {
        final var department = Department.builder()
                .code("department code2")
                .name("department name")
                .address("department address")
                .build();
        departmentRepository.save(department);

        assertThat(jdbcTemplate.queryForList("SELECT * FROM t_department"))
                .map(stringObjectMap -> stringObjectMap.get("c_id"))
                .map(Object::toString)
                .map(Long::parseLong)
                .contains(department.getId());
    }

}