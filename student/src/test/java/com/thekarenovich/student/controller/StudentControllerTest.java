package com.thekarenovich.student.controller;

import com.thekarenovich.student.model.Student;
import com.thekarenovich.student.repository.StudentRepository;
import com.thekarenovich.student.service.StudentService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentControllerTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    @BeforeAll
    public void init() {

    }

    @AfterAll
    public void delete() {
        studentRepository.deleteAll();
    }

    @Test
    void save() {
        var student = Student.builder()
                .id(123)
                .firstname("someString")
                .lastname("someString")
                .email("someString")
                .schoolId(123)
                .build();

        studentService.saveStudent(student);

        assertEquals(student.getFirstname(),
                studentService.findAllStudentsBySchool(student.getId()).stream()
                        .findFirst()
                        .get()
                        .getFirstname());

    }
}