package com.infodevelopers.ocsm.dto.userDto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class LoginDto {
    private String userName;
    private String password;
}
