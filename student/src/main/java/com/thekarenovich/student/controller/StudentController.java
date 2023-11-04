package com.thekarenovich.student.controller;

import com.thekarenovich.student.model.Student;
import com.thekarenovich.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity save(
            @RequestBody Student student
    ) {
        var savedStudent = service.saveStudent(student);
        return new ResponseEntity(savedStudent, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Student>> findAllStudents() {
        return ResponseEntity.ok(service.findAllStudents());
    }

    @GetMapping("/school/{school-id}")
    public ResponseEntity<List<Student>> findAllStudentsBySchool(
            @PathVariable("school-id") Integer schoolId
    ) {
        return ResponseEntity.ok(service.findAllStudentsBySchool(schoolId));
    }

    @GetMapping("/{student-id}")
    public ResponseEntity<Student> findStudentById(
            @PathVariable("student-id") Integer studentId
    ) {
        return ResponseEntity.ok(service.findStudentById(studentId));
    }

    @GetMapping("/{id}/{fieldName}/{newValue}")
    public ResponseEntity updateStudentField(
            @PathVariable("id") Integer id,
            @PathVariable("fieldName") String fieldName,
            @PathVariable("newValue") String newValue
    ) {
        var updatedStudent = service.updateStudentField(id, fieldName, newValue);
        return new ResponseEntity(updatedStudent, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{student-id}")
    public ResponseEntity deleteStudentById(
            @PathVariable("student-id") Integer studentId
    ) {
        var deletedStudent = service.deleteStudentById(studentId);
        return new ResponseEntity(deletedStudent, HttpStatus.NO_CONTENT);
    }

}