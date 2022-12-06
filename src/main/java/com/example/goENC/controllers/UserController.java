package com.example.goENC.controllers;

import com.example.goENC.dto.UserRequestDto;
import com.example.goENC.services.SurveyService;
import com.example.goENC.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@ResponseBody
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private final UserService userService;

    private final SurveyService surveyService;

    @PostMapping()
    public Long createUser(@RequestBody UserRequestDto requestDto) {
        return userService.createUser(requestDto);
    }

    @GetMapping(value = "/survey/{surveyId}")
    public Long getUserBySurvey(@PathVariable Long surveyId){
        return surveyService.getUserBySurvey(surveyId);
    }
}
