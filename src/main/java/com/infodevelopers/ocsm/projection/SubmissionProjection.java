package com.infodevelopers.ocsm.projection;

import java.util.Date;

public interface SubmissionProjection {
    Integer getId();
    Date getSubmissionDate();
    String getGrade();
    Integer getAssignmentId();
    Integer getUserId();
    String getFilePath();
}