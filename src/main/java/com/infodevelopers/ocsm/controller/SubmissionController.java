package com.infodevelopers.ocsm.controller;

import com.infodevelopers.ocsm.dto.GlobalApiResponse;
import com.infodevelopers.ocsm.dto.submissionDto.SubmissionDto;
import com.infodevelopers.ocsm.projection.SubmissionProjection;
import com.infodevelopers.ocsm.service.submission.SubmissionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/submission")
public class SubmissionController extends BaseController {
    private final SubmissionService submissionService;

    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @PostMapping("/submit")
    public ResponseEntity<GlobalApiResponse> submitAssignment(@ModelAttribute SubmissionDto submissionDto) throws Exception {
        SubmissionDto dto = submissionService.create(submissionDto);
        if (dto != null) {
            return new ResponseEntity<>(successResponse("Assignment Submit Successful", dto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(failureResponse("Assignment Submission failed", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/grade/assignment/{assignmentId}/user/{userId}")
    public ResponseEntity<GlobalApiResponse> gradeAssignment(@PathVariable(name = "assignmentId") Integer assignmentId, @PathVariable(name = "userId") Integer userId, @RequestParam(value = "grade", required = false) String grade) throws Exception {
        SubmissionDto dto = submissionService.gradeAssignment(assignmentId, userId, grade);
        if (dto != null) {
            return new ResponseEntity<>(successResponse("Submission Graded Successfully", dto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(failureResponse("Submission Grading failed", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/submissions/{assignmentId}")
    public ResponseEntity<GlobalApiResponse> viewSubmissionsByAssignmentId(@PathVariable(name = "assignmentId") Integer assignmentId) {
        List<SubmissionDto> dtoList = submissionService.viewSubmissionsByAssignmentId(assignmentId);
        if (dtoList != null) {
            return new ResponseEntity<>(successResponse("Submissions fetched Successfully", dtoList), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(failureResponse("Submissions fetch failed", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/submissionsList")
    public ResponseEntity<GlobalApiResponse> viewAll(){
        List<SubmissionProjection> submissions = submissionService.findAllSubmissions();
        if (submissions != null) {
            return new ResponseEntity<>(successResponse("Submissions fetched Successfully", submissions), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(failureResponse("Submissions fetch failed", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
