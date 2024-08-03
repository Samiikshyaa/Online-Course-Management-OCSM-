package com.infodevelopers.ocsm.repository;

import com.infodevelopers.ocsm.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {
}
