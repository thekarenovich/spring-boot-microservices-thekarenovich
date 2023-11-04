package com.thekarenovich.school.service;

import com.thekarenovich.school.model.School;
import com.thekarenovich.school.response.FullSchoolResponse;

import java.util.List;

public interface SchoolService {

    School saveSchool(School school);

    List<School> findAllSchools();

    FullSchoolResponse findSchoolsWithStudents(Integer schoolId);

    School findSchoolById(Integer schoolId);

    School deleteSchoolById(Integer schoolId);
}
