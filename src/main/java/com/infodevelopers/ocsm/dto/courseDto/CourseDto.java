package com.infodevelopers.ocsm.dto.courseDto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class CourseDto {

    private Integer id;

    private String courseName;

    private String description;

    private String schedule;

    private List<String> instructors;
}
