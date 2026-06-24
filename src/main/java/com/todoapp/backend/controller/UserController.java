package com.todoapp.backend.controller;

import com.todoapp.backend.dto.UserDto;
import com.todoapp.backend.service.TaskService;
import com.todoapp.backend.service.UserService;
import jakarta.validation.Valid;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> saveUser( @Valid @RequestBody UserDto userDto){
        UserDto savedUser = userService.saveUser(userDto);
        return ResponseEntity.ok().body(savedUser);
    }
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users=userService.getAllUsers();
        return ResponseEntity.ok().body(users);

    }

}
