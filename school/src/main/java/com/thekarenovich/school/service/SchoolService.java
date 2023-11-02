package com.thekarenovich.school.service;

import com.thekarenovich.school.model.School;
import com.thekarenovich.school.response.FullSchoolResponse;

import java.util.List;

public interface SchoolService {

    void saveSchool(School school);

    List<School> findAllSchools();

    FullSchoolResponse findSchoolsWithStudents(Integer schoolId);

    School findSchoolById(Integer schoolId);

    void deleteSchoolById(Integer schoolId);
}
