package com.herman87.simplewebapp;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class StudentTest {

    @Test
    void admitTest() {
        Student student = new Student(10f);
        Teacher teacher = mock(Teacher.class);

        when(teacher.isSucceed(student)).thenReturn("tres bien");

        assertThat(student.admit(teacher))
                .isEqualTo("excellent");
    }

    @Test
    void isSucceedTest() {
        Teacher teacher = new Teacher();
        Student student = new Student(10f);

        assertThat(teacher.isSucceed(student))
                .isEqualTo("passable");
    }
}
