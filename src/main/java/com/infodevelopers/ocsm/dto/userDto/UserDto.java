package com.infodevelopers.ocsm.dto.userDto;

import com.infodevelopers.ocsm.dto.roleDto.RoleDto;
import com.infodevelopers.ocsm.entity.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class UserDto {
    private Integer id;

    private String userName;

    private String password;

    private String email;

    private String mobileNumber;

    private List<String> role;
}
