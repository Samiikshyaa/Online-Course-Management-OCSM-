package com.infodevelopers.ocsm.service.assignment;

import com.infodevelopers.ocsm.dto.assignmentDto.AssignmentDto;
import com.infodevelopers.ocsm.service.GenericService;

import java.util.List;

public interface AssignmentService extends GenericService<AssignmentDto> {
    List<AssignmentDto> assignmentOfParticularCourse(Integer id);

//    AssignmentDto createAssignment(AssignmentDto assignmentDto, MultipartFile file);
}
