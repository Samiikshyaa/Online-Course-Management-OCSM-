package com.infodevelopers.ocsm.controller;

import com.infodevelopers.ocsm.dto.GlobalApiResponse;
import com.infodevelopers.ocsm.dto.courseDto.CourseDto;
import com.infodevelopers.ocsm.service.course.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course")
public class CourseController extends BaseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }


    @PostMapping("/create")
    public ResponseEntity<GlobalApiResponse> createCourse(@RequestBody CourseDto courseDto) {
        CourseDto course = courseService.create(courseDto);

        if (course != null) {
            return new ResponseEntity<>(successResponse("Course Created successfully", courseDto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(successResponse("Course Creation failed", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
