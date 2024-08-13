package com.infodevelopers.ocsm.dto.submissionDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.infodevelopers.ocsm.entity.Assignment;
import com.infodevelopers.ocsm.entity.User;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class SubmissionDto {
    private Integer id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date submissionDate;
    private String grade;
    private Integer assignmentId;
    private Integer userId;
    private String filePath;
    @JsonIgnore
    private MultipartFile file;
}
