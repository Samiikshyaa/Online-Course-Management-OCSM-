package com.infodevelopers.ocsm.service.enrollment;

import com.infodevelopers.ocsm.dto.enrollmentDto.EnrollmentDto;
import com.infodevelopers.ocsm.service.GenericService;

import java.util.List;

public interface EnrollmentService extends GenericService<EnrollmentDto> {
    List<EnrollmentDto> findByMobileNumber(String mobileNumber);

//    List<EnrollmentDto> findByUserName(String userName);
}
