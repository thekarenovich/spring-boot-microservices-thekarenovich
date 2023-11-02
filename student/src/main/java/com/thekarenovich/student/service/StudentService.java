package com.thekarenovich.student.service;

import com.thekarenovich.student.model.Student;

import java.util.List;

public interface StudentService {

    void saveStudent(Student student);

    List<Student> findAllStudents();

    List<Student> findAllStudentsBySchool(Integer schoolId);

    Student findStudentById(Integer id);

    List<Student> findStudentBySpecificId(Integer num);
}
