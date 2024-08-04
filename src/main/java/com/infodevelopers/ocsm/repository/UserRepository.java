package com.infodevelopers.ocsm.repository;

import com.infodevelopers.ocsm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "select * from tbl_user where user_name = ?1 and password = ?2", nativeQuery = true)
    User findByUsernameAndPassword(String username, String password);

    @Query(value = "select * from tbl_user where user_name in(?1)", nativeQuery = true)
    List<User> findByAllUserName(List<String> username);

    @Query(value = "SELECT * from tbl_user u INNER JOIN user_role ur on u.id = ur.user_id where ur.role_id = 2 and u.user_name = 'samikshya'",nativeQuery = true)
    User findStudentByUserName(String username, String role);
}
