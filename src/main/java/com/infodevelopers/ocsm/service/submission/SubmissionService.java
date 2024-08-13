package com.infodevelopers.ocsm.service.submission;

import com.infodevelopers.ocsm.dto.submissionDto.SubmissionDto;
import com.infodevelopers.ocsm.projection.SubmissionProjection;
import com.infodevelopers.ocsm.service.GenericService;

import java.util.List;

public interface SubmissionService extends GenericService<SubmissionDto> {

    SubmissionDto gradeAssignment(Integer assignmentId, Integer userId, String grade);

    List<SubmissionDto> viewSubmissionsByAssignmentId(Integer assignmentId);

    List<SubmissionProjection> findAllSubmissions();
}

