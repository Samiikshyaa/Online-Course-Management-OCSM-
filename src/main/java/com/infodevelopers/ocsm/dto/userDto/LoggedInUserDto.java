package com.infodevelopers.ocsm.dto.userDto;

import lombok.*;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class LoggedInUserDto {
        private Integer id;

        private String email;

        private String mobileNumber;

        private List<String> role;
}
