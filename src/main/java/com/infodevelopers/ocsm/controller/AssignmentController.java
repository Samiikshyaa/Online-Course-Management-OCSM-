package com.infodevelopers.ocsm.controller;

import com.infodevelopers.ocsm.dto.GlobalApiResponse;
import com.infodevelopers.ocsm.dto.assignmentDto.AssignmentDto;
import com.infodevelopers.ocsm.dto.assignmentDto.AssignmentResponseDto;
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

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }


    @PostMapping("/create")
    public ResponseEntity<GlobalApiResponse> createAssignment(@ModelAttribute AssignmentDto assignmentDto) throws Exception {

        AssignmentDto dto = assignmentService.create(assignmentDto);

        if (dto != null) {
            return new ResponseEntity<>(successResponse("Assignment Created Successfully", dto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(failureResponse("Assignment Creation failed", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<GlobalApiResponse> updateAssignment(@ModelAttribute AssignmentDto assignmentDto) {
        AssignmentDto dto = assignmentService.update(assignmentDto);
        if (dto != null) {
            return new ResponseEntity<>(successResponse("Assignment Created Successfully", dto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(failureResponse("Assignment Creation failed", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/assignmentList/ofCourse/{courseId}")
    public ResponseEntity<GlobalApiResponse> getAssignmentOfTheCourse(@PathVariable("courseId") Integer courseId) {
        List<AssignmentResponseDto> assignmentDto = assignmentService.assignmentOfParticularCourse(courseId);
        if (assignmentDto != null) {
            return new ResponseEntity<>(successResponse("Assignment fetched Successfully", assignmentDto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(failureResponse("Assignment fetch failed", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/assignmentList")
    public ResponseEntity<GlobalApiResponse> getAssignments() {
        List<AssignmentDto> assignmentDto = assignmentService.findAll();
        if (assignmentDto != null) {
            return new ResponseEntity<>(successResponse("Assignment fetched Successfully", assignmentDto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(failureResponse("Assignment fetch failed", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteAssignment/{assignmentId}")
    public ResponseEntity<GlobalApiResponse> deleteAssignment(@PathVariable("assignmentId") Integer id){
        assignmentService.deleteById(id);
        boolean flag = assignmentService.findById(id);
        if (flag == true){
            return new ResponseEntity<>(successResponse("Course deleted successfully",id),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(failureResponse("Course still exists",id),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
