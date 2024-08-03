package com.infodevelopers.ocsm.repository;

import com.infodevelopers.ocsm.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    Course findByCourseNameAndAndDescription(String courseName, String courseDescription);
}
