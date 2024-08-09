package com.infodevelopers.ocsm.controller;

import com.infodevelopers.ocsm.dto.GlobalApiResponse;
import com.infodevelopers.ocsm.dto.assignmentDto.AssignmentDto;
import com.infodevelopers.ocsm.service.FileStorageService;
import com.infodevelopers.ocsm.service.assignment.AssignmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assignment")
public class AssignmentController extends BaseController {
    private final AssignmentService assignmentService;
    private final FileStorageService fileStorageService;

    public AssignmentController(AssignmentService assignmentService, FileStorageService fileStorageService) {
        this.assignmentService = assignmentService;
        this.fileStorageService = fileStorageService;
    }


    @PostMapping("/create")
    public ResponseEntity<GlobalApiResponse> createAssignment(@ModelAttribute AssignmentDto assignmentDto) {

        AssignmentDto dto = assignmentService.create(assignmentDto);

        if (dto != null) {
            return new ResponseEntity<>(successResponse("Assignment Created Successfully", dto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(failureResponse("Assignment Creation failed", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/assignments/ofCourse/{courseId}")
    public ResponseEntity<GlobalApiResponse> getAssignmentOfTheCourse(@PathVariable("courseId") Integer courseId) {
        List<AssignmentDto> assignmentDto = assignmentService.assignmentOfParticularCourse(courseId);
        if (assignmentDto != null) {
            return new ResponseEntity<>(successResponse("Assignment fetched Successfully", assignmentDto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(failureResponse("Assignment fetch failed", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
