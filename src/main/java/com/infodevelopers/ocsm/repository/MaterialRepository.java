package com.infodevelopers.ocsm.repository;

import com.infodevelopers.ocsm.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, Integer> {
    @Query(value = "select * from tbl_material where course_id = ?1", nativeQuery = true)
    List<Material> findByCourse_Id(Integer courseId);
}
