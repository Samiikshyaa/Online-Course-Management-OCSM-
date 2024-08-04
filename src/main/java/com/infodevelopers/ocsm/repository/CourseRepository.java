package com.infodevelopers.ocsm.repository;

import com.infodevelopers.ocsm.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    @Query(value = "select * from tbl_course where course_name = ?1 and  description = ?2", nativeQuery = true)
    Course findByCourseNameAndAndDescription(String courseName, String courseDescription);
}
