package com.thekarenovich.school.controller;

import com.thekarenovich.school.model.School;
import com.thekarenovich.school.repository.SchoolRepository;
import com.thekarenovich.school.service.SchoolService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.ArgumentMatchers.anyInt;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SchoolControllerTest {

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private SchoolService schoolService;

    @BeforeAll
    public void init() {
        var sch1 = new School(1, "school1", "school1@edu.com");
        var sch2 = new School(2, "school2", "school2@edu.com");
        schoolRepository.saveAll(List.of(sch1, sch2));
    }

    @AfterAll
    public void delete() {
        schoolRepository.deleteAll();
    }

    @Test
    public void testNotExistSchool() {
        assertEquals("NOT_FOUND", schoolService.findSchoolById(anyInt()).getName());
    }

    @Test
    public void testFindSchoolById() {
        School school = schoolService.findSchoolById(1);

        List<Object> actualSchool = new ArrayList<>();
        actualSchool.add(school.getId());
        actualSchool.add(school.getName());
        actualSchool.add(school.getEmail());

        assertIterableEquals(actualSchool, List.of(1, "school1", "school1@edu.com"));
    }


}


