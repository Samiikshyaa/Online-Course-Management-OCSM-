package com.infodevelopers.ocsm.dto.assignmentDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class AssignmentDto {

    private Integer id;

    private String title;

    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dueDate;

    private String filePath;

    private Integer courseId;

    @JsonIgnore
    private MultipartFile file;
}
