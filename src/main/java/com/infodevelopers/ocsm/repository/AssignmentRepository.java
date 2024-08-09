package com.infodevelopers.ocsm.repository;

import com.infodevelopers.ocsm.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {

    @Query(value = "select * from tbl_assignment where course_id =?1", nativeQuery = true)
    Optional<List<Assignment>> findByCourseId(Integer CourseId);

}
