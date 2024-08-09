package com.infodevelopers.ocsm.repository;

import com.infodevelopers.ocsm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "select * from tbl_user where user_name = ?1 and password = ?2", nativeQuery = true)
    User findByUsernameAndPassword(String username, String password);

    @Query(value = "select * from tbl_user where user_name in(?1)", nativeQuery = true)
    List<User> findByAllUserName(List<String> username);

    @Query(value = "SELECT * from tbl_user where mobile_number = ?1", nativeQuery = true)
    Optional<User> findUserByMobileNumber(String phone);

//    Optional<User> findByUserName(String username);
}
