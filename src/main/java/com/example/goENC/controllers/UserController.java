package com.example.goENC.controllers;

import com.example.goENC.dto.UserRequestDto;
import com.example.goENC.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@ResponseBody
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private final UserService userService;

    @PostMapping("/create")
    public Long createUser(@RequestBody UserRequestDto requestDto) {
        return userService.createUser(requestDto);
    }
}
