package com.infodevelopers.ocsm.repository;

import com.infodevelopers.ocsm.dto.roleDto.RoleDto;
import com.infodevelopers.ocsm.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query(nativeQuery = true, value = "select * from tbl_role where role_name = ?1")
    Role findRoleByRoleName(String roleName);

    @Query(value = "select * from tbl_role where role_name in (?1)",nativeQuery = true)
    List<Role>  findAllByName(List<String> role);
}
