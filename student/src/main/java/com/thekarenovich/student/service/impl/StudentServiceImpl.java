package com.thekarenovich.student.service.impl;

import com.thekarenovich.student.model.Student;
import com.thekarenovich.student.repository.StudentRepository;
import com.thekarenovich.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;

    public void saveStudent(Student student) {
        repository.save(student);
    }

    public List<Student> findAllStudents() {
        return repository.findAll();
    }

    public List<Student> findAllStudentsBySchool(Integer schoolId) {

        var students = repository.findAllBySchoolId(schoolId);

        if (students.isEmpty()) {
            students = List.of(Student.builder()
                    .firstname("NOT_FOUND")
                    .lastname("NOT_FOUND")
                    .email("NOT_FOUND")
                    .schoolId(null)
                    .build()
            );
        }

        return students;
    }

    public Student findStudentById(Integer studentId) {

        return repository.findById(studentId)
                .orElse(
                        Student.builder()
                                .firstname("NOT_FOUND")
                                .lastname("NOT_FOUND")
                                .email("NOT_FOUND")
                                .schoolId(null)
                                .build()
                );
    }

    public List<Student> findStudentBySpecificId(Integer num) {

        return repository.findAll().stream()
                .filter(student -> !student.getId().equals(num))
                .collect(Collectors.toList());
    }

}
