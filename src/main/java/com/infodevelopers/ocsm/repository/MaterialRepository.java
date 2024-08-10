package com.infodevelopers.ocsm.repository;

import com.infodevelopers.ocsm.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Material, Integer> {
}
