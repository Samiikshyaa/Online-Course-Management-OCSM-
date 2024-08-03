package com.infodevelopers.ocsm.controller;

import com.infodevelopers.ocsm.dto.GlobalApiResponse;
import com.infodevelopers.ocsm.dto.userDto.LoggedInUserDto;
import com.infodevelopers.ocsm.dto.userDto.LoginDto;
import com.infodevelopers.ocsm.dto.userDto.UserDto;
import com.infodevelopers.ocsm.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<GlobalApiResponse> signup(@RequestBody UserDto userDto){
        UserDto u = userService.create(userDto);
        if(u!= null){
            return new ResponseEntity<>(successResponse("User Created",userDto), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(successResponse("User Creation failed",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/login")
    public ResponseEntity<GlobalApiResponse> signup(@ModelAttribute LoginDto loginDto){
        LoggedInUserDto u = userService.findUserByUserNameAndPassword(loginDto.getUserName(), loginDto.getPassword());
        if(u!= null){
            return new ResponseEntity<>(successResponse("User Created",u), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(successResponse("User Creation failed",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
