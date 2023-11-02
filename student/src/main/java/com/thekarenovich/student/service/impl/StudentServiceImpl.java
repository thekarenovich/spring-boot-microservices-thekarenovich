package com.thekarenovich.student.service.impl;

import com.thekarenovich.student.client.SchoolClient;
import com.thekarenovich.student.exception.AlreadyExistEntityException;
import com.thekarenovich.student.exception.NotFoundEntityException;
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

    private final StudentRepository studentRepository;
    private final SchoolClient client;

    public void saveStudent(Student student) {

        var checkSchool = client.findSchoolById(student.getSchoolId());

        if (!checkSchool.getName().equals("NOT_FOUND")) {
            var checkStudent = studentRepository.findAll().stream().anyMatch(i -> i.getId() == student.getId());

            if (!checkStudent)
                studentRepository.save(student);
            else
                throw new AlreadyExistEntityException("ExceptionMessage: student with id %d already exists"
                        .formatted(student.getId()));
        } else
            throw new NotFoundEntityException("ExceptionMessage: school with id %d not exists"
                    .formatted(student.getSchoolId()));
    }

    public void saveStudentWithoutCheck(Student student) {
        studentRepository.save(student);
    }

    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> findAllStudentsBySchool(Integer schoolId) {

        var students = studentRepository.findAllBySchoolId(schoolId);

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

        return studentRepository.findById(studentId)
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

        return studentRepository.findAll().stream()
                .filter(student -> !student.getId().equals(num))
                .collect(Collectors.toList());
    }

    public void updateStudentField(Integer studentId, String fieldName, String newValue) {
        var studentOptional = studentRepository.findById(studentId);

        if (studentOptional.isPresent()) {
            var student = studentOptional.get();

            switch (fieldName) {
                case "firstname":
                    student.setFirstname(newValue);
                    break;
                case "lastname":
                    student.setLastname(newValue);
                    break;
                case "email":
                    student.setEmail(newValue);
                    break;
                case "schoolId":
                    student.setSchoolId(Integer.parseInt(newValue));
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field name: " + fieldName);
            }

            saveStudentWithoutCheck(student);
        } else {
            throw new IllegalArgumentException("Student not found with ID: " + studentId);
        }
    }

    public void deleteStudentById(Integer studentId) {
        studentRepository.deleteById(studentId);
    }

}
