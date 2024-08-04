package com.infodevelopers.ocsm.controller;

import com.infodevelopers.ocsm.dto.GlobalApiResponse;
import com.infodevelopers.ocsm.dto.enrollmentDto.EnrollmentDto;
import com.infodevelopers.ocsm.service.enrollment.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollment")
public class EnrollmentController extends BaseController {

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping("/enroll")
    public ResponseEntity<GlobalApiResponse> createEnrollment(@RequestBody EnrollmentDto enrollmentDto){
        EnrollmentDto dto = enrollmentService.create(enrollmentDto);
        if (dto != null){
            return new ResponseEntity<>(successResponse("Enrollment Started successfully",enrollmentDto), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(failureResponse("Enrollment failed",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/enrollmentList")
    public ResponseEntity<GlobalApiResponse> findAllEnrollment(EnrollmentDto enrollmentDto){
        List<EnrollmentDto> enrollmentDtoList =  enrollmentService.findAll();
        if (enrollmentDtoList != null){
            return new ResponseEntity<>(successResponse("Enrollment Started successfully",enrollmentDtoList), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(failureResponse("Enrollment failed",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
