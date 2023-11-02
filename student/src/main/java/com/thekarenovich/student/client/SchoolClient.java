package com.thekarenovich.student.client;

import com.thekarenovich.student.model.School;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "school-service", url = "${application.config.schools-url}")
public interface SchoolClient {

    @GetMapping("/{school-id}")
    School findSchoolById(@PathVariable("school-id") Integer schoolId);
}
