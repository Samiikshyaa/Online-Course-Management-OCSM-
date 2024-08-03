package com.infodevelopers.ocsm.loader;

import com.infodevelopers.ocsm.entity.Role;
import com.infodevelopers.ocsm.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class MasterDataLoader {

    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    public void loadData() {
        createDataIfNotExist();
    }

    private void createDataIfNotExist() {
        List<Role> roleList = new ArrayList<>();
        roleList.add(Role.builder().roleName("student").build());
        roleList.add(Role.builder().roleName("instructor").build());

        for (Role r : roleList) {
            Role role = roleRepository.findRoleByRoleName(r.getRoleName());
            if (role == null) {
                roleRepository.save(r);
                log.info("{} role saved ", r.getRoleName());
            } else {
                log.info("{} role already saved ", r.getRoleName());
            }
        }

//        Permission viewCourse = new Permission(null, "view course");
//        Permission editCourse = new Permission(null, "edit course");
//        Permission deleteCourse = new Permission(null, "delete course");
//        Permission createCourse = new Permission(null, "create course");
//        Permission enrollInCourse = new Permission(null, "enroll in course");
//        Permission viewEnrollment = new Permission(null, "view enrollment");
//        Permission createAssignment = new Permission(null, "create assignment");
//        Permission viewAssignment = new Permission(null, "view assignment");
//        Permission submitAssignment = new Permission(null, "submit assignment");
//        Permission gradeAssignment = new Permission(null, "grade assignment");
//        Permission viewGrade = new Permission(null, "view grade");
//        Permission createMaterial = new Permission(null, "create material");
//        Permission viewMaterial = new Permission(null, "view material");
//        Permission editMaterial = new Permission(null, "edit material");
//        Permission deleteMaterial = new Permission(null, "delete material");
//        Permission downloadMaterial = new Permission(null, "download material");
//
//        List<Permission> permissions = new ArrayList<>();
//        permissions.add(viewCourse);
//        permissions.add(editCourse);
//        permissions.add(deleteCourse);
//        permissions.add(createCourse);
//        permissions.add(enrollInCourse);
//        permissions.add(viewEnrollment);
//        permissions.add(createAssignment);
//        permissions.add(viewAssignment);
//        permissions.add(submitAssignment);
//        permissions.add(gradeAssignment);
//        permissions.add(viewGrade);
//        permissions.add(createMaterial);
//        permissions.add(viewMaterial);
//        permissions.add(editMaterial);
//        permissions.add(deleteMaterial);
//        permissions.add(downloadMaterial);
//
//        List<Permission> studentPermission = List.of(viewCourse, enrollInCourse, viewEnrollment,
//                viewAssignment, submitAssignment, viewGrade, viewMaterial, downloadMaterial);
//
//        List<Permission> instructorPermission = List.of(viewCourse, editCourse, deleteCourse, createCourse,
//                viewEnrollment, createAssignment, viewAssignment, gradeAssignment, createMaterial, viewMaterial,
//                editMaterial, deleteMaterial);


    }
}
