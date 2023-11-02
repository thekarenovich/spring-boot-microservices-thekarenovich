package com.thekarenovich.school.controller;

import com.thekarenovich.school.model.School;
import com.thekarenovich.school.response.FullSchoolResponse;
import com.thekarenovich.school.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/schools")
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(
            @RequestBody School school
    ) {
        service.saveSchool(school);
    }

    @GetMapping
    public ResponseEntity<List<School>> findAllSchools() {
        return ResponseEntity.ok(service.findAllSchools());
    }

    @GetMapping("/with-students/{school-id}")
    public ResponseEntity<FullSchoolResponse> findSchoolsWithStudents(
            @PathVariable("school-id") Integer schoolId
    ) {
        return ResponseEntity.ok(service.findSchoolsWithStudents(schoolId));
    }

    @GetMapping("/{school-id}")
    public ResponseEntity<School> findSchoolById(
            @PathVariable("school-id") Integer schoolId
    ) {
        return ResponseEntity.ok(service.findSchoolById(schoolId));
    }

    @DeleteMapping("/{school-id}")
    public void deleteSchoolById(
            @PathVariable("school-id") Integer schoolId
    ) {
        service.deleteSchoolById(schoolId);
    }

}
