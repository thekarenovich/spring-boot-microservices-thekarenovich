package com.thekarenovich.student.service;

import com.thekarenovich.student.model.Student;

import java.util.List;

public interface StudentService {

    Student saveStudent(Student student);

    List<Student> findAllStudents();

    List<Student> findAllStudentsBySchool(Integer schoolId);

    Student findStudentById(Integer id);

    List<Student> findStudentBySpecificId(Integer num);

    Student updateStudentField(Integer studentId, String fieldName, String newValue);

    Student deleteStudentById(Integer studentId);

}
