package com.infodevelopers.ocsm.service.user;

import com.infodevelopers.ocsm.dto.userDto.LoggedInUserDto;
import com.infodevelopers.ocsm.dto.userDto.UserDto;
import com.infodevelopers.ocsm.service.GenericService;

public interface UserService extends GenericService<UserDto> {

    LoggedInUserDto findUserByUserNameAndPassword(String username, String password);
}
