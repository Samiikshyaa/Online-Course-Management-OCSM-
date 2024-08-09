package com.infodevelopers.ocsm.controller;

import com.infodevelopers.ocsm.dto.GlobalApiResponse;
import com.infodevelopers.ocsm.dto.courseDto.CourseDto;
import com.infodevelopers.ocsm.service.course.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @GetMapping("/courseList")
    public ResponseEntity<GlobalApiResponse> getAllCourses(){
        List<CourseDto> courseDto = courseService.findAll();
        if (courseDto != null){
            return new ResponseEntity<>(successResponse("Course fetched successfully",courseDto),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(failureResponse("Course fetch failed",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/updateCourse")
    public ResponseEntity<GlobalApiResponse> updateCourse(@RequestBody CourseDto courseDto){
        CourseDto updated = courseService.update(courseDto);
        if (courseDto != null){
            return new ResponseEntity<>(successResponse("Course fetched successfully",courseDto),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(failureResponse("Course fetch failed",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/deleteCourse/{courseId}")
    public ResponseEntity<GlobalApiResponse> deleteCourse(@PathVariable("courseId") Integer id){
        courseService.deleteById(id);
        boolean flag = courseService.findById(id);
        if (flag == true){
            return new ResponseEntity<>(successResponse("Course deleted successfully",id),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(failureResponse("Course still exists",id),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
