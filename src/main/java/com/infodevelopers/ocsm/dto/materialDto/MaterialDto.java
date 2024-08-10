package com.infodevelopers.ocsm.dto.materialDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.infodevelopers.ocsm.entity.Course;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class MaterialDto {
    private Integer id;
    private String title;
    private String content;
    private String materialPath;
    private Integer courseId;
    @JsonIgnore
    private MultipartFile file;
}
