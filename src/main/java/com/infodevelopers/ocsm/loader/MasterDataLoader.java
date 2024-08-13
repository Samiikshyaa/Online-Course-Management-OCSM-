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
    }
}
