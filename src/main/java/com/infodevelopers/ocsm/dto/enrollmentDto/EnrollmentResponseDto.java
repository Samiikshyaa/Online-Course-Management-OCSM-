package com.infodevelopers.ocsm.dto.enrollmentDto;

import lombok.*;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class EnrollmentResponseDto {
    private Integer id;

    private Date enrolledAt;

    private String mobileNumber;

    private String course;

    private String description;
}
