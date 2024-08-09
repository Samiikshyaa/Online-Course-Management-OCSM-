package com.infodevelopers.ocsm.repository;

import com.infodevelopers.ocsm.dto.enrollmentDto.EnrollmentDto;
import com.infodevelopers.ocsm.entity.Enrollment;
import com.infodevelopers.ocsm.service.enrollment.EnrollmentService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {
    @Query(nativeQuery = true, value = "SELECT e.* from tbl_enrollment e  left join tbl_user u on u.id = e.user_id where u.mobile_number= ?1")
    List<Enrollment> findByMobileNumber(String mobileNumber);
    List<Enrollment> findAll();
}
