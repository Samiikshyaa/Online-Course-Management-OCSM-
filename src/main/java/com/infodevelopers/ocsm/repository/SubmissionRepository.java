package com.infodevelopers.ocsm.repository;

import com.infodevelopers.ocsm.entity.Submission;
import com.infodevelopers.ocsm.projection.SubmissionProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, Integer> {

    @Query(value = "select * from tbl_submission where assignment_id = ?1 and user_id = ?2", nativeQuery = true)
    Submission findByAssignmentIdAndUserId(Integer assignmentId, Integer userId);

    @Query(value = "select * from tbl_submission where assignment_id = ?1", nativeQuery = true)
    List<Submission> findByAssignmentId(Integer assignmentId);

    @Query(value = "select s.id as id,s.submission_date as submissionDate, s.grade as grade, s.assignment_id as assignmentId, s.user_id as userId, s.file_path as filePath from tbl_submission s", nativeQuery = true)
    List<SubmissionProjection> findAllProjections();

}
