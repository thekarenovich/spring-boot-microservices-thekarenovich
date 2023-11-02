package com.thekarenovich.school.service.impl;

import com.thekarenovich.school.client.StudentClient;
import com.thekarenovich.school.exception.AlreadyExistEntityException;
import com.thekarenovich.school.exception.NotFoundEntityException;
import com.thekarenovich.school.model.School;
import com.thekarenovich.school.model.Student;
import com.thekarenovich.school.repository.SchoolRepository;
import com.thekarenovich.school.response.FullSchoolResponse;
import com.thekarenovich.school.service.SchoolService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository schoolRepository;
    private final StudentClient client;

    public void saveSchool(School school) {

        var check = schoolRepository.findAll().stream().anyMatch(i -> i.getId() == school.getId());

        if (!check)
            schoolRepository.save(school);
        else
            throw new AlreadyExistEntityException("ExceptionMessage: school with id %d already exists"
                    .formatted(school.getId()));


    }

    private void saveSchoolWithoutCheck(School school) {
        schoolRepository.save(school);
    }

    public List<School> findAllSchools() {
        return schoolRepository.findAll();
    }

    public FullSchoolResponse findSchoolsWithStudents(Integer schoolId) {
        var school = schoolRepository.findById(schoolId)
                .orElse(
                        School.builder()
                                .name("NOT_FOUND")
                                .email("NOT_FOUND")
                                .build()
                );
        var students = client.findAllStudentsBySchool(schoolId);
        return FullSchoolResponse.builder()
                .name(school.getName())
                .email(school.getEmail())
                .students(students)
                .build();
    }

    public School findSchoolById(Integer schoolId) {

        return schoolRepository.findById(schoolId)
                .orElse(
                        School.builder()
                                .name("NOT_FOUND")
                                .email("NOT_FOUND")
                                .build()
                );
    }

    public void deleteSchoolById(Integer schoolId) {

        if (findSchoolById(schoolId).getName().equals("NOT_FOUND"))
            throw new NotFoundEntityException("ExceptionMessage: school with id %d not exists"
                    .formatted(schoolId));

        List<School> schools = findAllSchools();

        List<Student> students = client.findAllStudentsBySchool(schoolId);

        Map<Integer, Integer> idCountMap = new HashMap<>();

        for (Student student : students) {
            int currentSchoolId = student.getSchoolId();
            idCountMap.put(currentSchoolId, idCountMap.getOrDefault(currentSchoolId, 0) + 1);
        }

        if (schools.size() > 1) {
            int minCount = Integer.MAX_VALUE;
            int minCountSchoolId = 0;

            for (School school : schools) {
                int currentSchoolId = school.getId();
                int studentCount = idCountMap.getOrDefault(currentSchoolId, 0);

                if (studentCount < minCount) {
                    minCount = studentCount;
                    minCountSchoolId = currentSchoolId;
                }
            }

            for (Student student : students)
                client.updateStudentField(student.getId(), "schoolId", String.valueOf(minCountSchoolId));


        } else {
            saveSchoolWithoutCheck(School.builder()
                    .name(RandomStringUtils.randomAlphanumeric(10))
                    .email(RandomStringUtils.randomAlphanumeric(10))
                    .build());

            for (Student student : students)
                client.updateStudentField(student.getId(), "schoolId", String.valueOf(findAllSchools().get(1).getId()));

        }

        schoolRepository.deleteById(schoolId);
    }
}
