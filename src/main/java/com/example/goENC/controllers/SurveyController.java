package com.example.goENC.controllers;

import com.example.goENC.demoCode.dto.book.BookAddRequestDto;
import com.example.goENC.dto.survey.RequestCreateSurveyDto;
import com.example.goENC.services.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@ResponseBody
@RequestMapping("/api/survey")
public class SurveyController {

    @Autowired
    private final SurveyService surveyService;

    @PostMapping
    public Integer createSurvey(@RequestBody RequestCreateSurveyDto requestDto) {

        return surveyService.createSurvey(requestDto);
    }
}
