package com.thekarenovich.school.client;

import com.thekarenovich.school.model.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "student-service", url = "${application.config.students-url}")
public interface StudentClient {

    @GetMapping("/school/{school-id}")
    List<Student> findAllStudentsBySchool(@PathVariable("school-id") Integer schoolId);

    @GetMapping("/{id}/{fieldName}/{newValue}")
    void updateStudentField(
            @PathVariable("id") Integer id,
            @PathVariable("fieldName") String fieldName,
            @PathVariable("newValue") String newValue
    );
}
