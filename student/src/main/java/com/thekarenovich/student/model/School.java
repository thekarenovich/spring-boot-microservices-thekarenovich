package com.thekarenovich.student.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class School {

    private Integer id;
    private String name;
    private String email;
}
