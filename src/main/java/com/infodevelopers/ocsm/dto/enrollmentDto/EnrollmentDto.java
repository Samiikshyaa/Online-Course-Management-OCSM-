package com.infodevelopers.ocsm.dto.enrollmentDto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class EnrollmentDto {
    private Integer id;

    private Date enrolledAt;

    private String userName;

    private String role;

    private String course;

    private String description;
}
