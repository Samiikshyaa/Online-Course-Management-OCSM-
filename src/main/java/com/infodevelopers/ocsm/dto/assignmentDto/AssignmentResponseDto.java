package com.infodevelopers.ocsm.dto.assignmentDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@JsonSerialize
@Getter
@Setter
public class AssignmentResponseDto {
    private Integer id;

    private String title;

    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dueDate;

    private String filePath;

    private String courseName;

}
