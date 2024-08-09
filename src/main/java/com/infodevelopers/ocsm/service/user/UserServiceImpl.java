package com.infodevelopers.ocsm.service.user;

import com.infodevelopers.ocsm.dto.userDto.LoggedInUserDto;
import com.infodevelopers.ocsm.dto.userDto.UserDto;
import com.infodevelopers.ocsm.entity.Role;
import com.infodevelopers.ocsm.entity.User;
import com.infodevelopers.ocsm.repository.RoleRepository;
import com.infodevelopers.ocsm.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@Slf4j

public class UserServiceImpl implements UserService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

//    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
    }



//Create new User
    @Override
    public UserDto create(UserDto userDto) {
        AtomicBoolean flag = new AtomicBoolean(false);
        List<Role> roles = new ArrayList<>();
        userDto.getRole().stream().forEach(data -> {
            Role r = roleRepository.findRoleByRoleName(data);
            if (r != null) {
                roles.add(r);
            } else {
                log.info("{} role not found", data);
            }
        });

//        String hashedPassword = passwordEncoder.encode(userDto.getPassword());
//        log.info("Hashed Password is : {}",hashedPassword);
        User user = User.builder()
                .id(userDto.getId())
                .userName(userDto.getUserName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .mobileNumber(userDto.getMobileNumber())
                .role(roles)
                .build();
        userRepository.save(user);

        return userDto;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public List<UserDto> findAll() {
        return null;
    }

    @Override
    public UserDto update(UserDto userDto) {
        return null;
    }

    @Override
    public LoggedInUserDto findUserByUserNameAndPassword(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        List<String> roles = new ArrayList<>();
        user.getRole().stream().forEach(data -> roles.add(data.getRoleName()));
        LoggedInUserDto dto = LoggedInUserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .mobileNumber(user.getMobileNumber())
                .role(roles)
                .build();

        return dto;
    }
}
