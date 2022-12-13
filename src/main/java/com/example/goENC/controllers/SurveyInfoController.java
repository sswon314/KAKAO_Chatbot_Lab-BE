package com.example.goENC.controllers;

import com.example.goENC.dto.SurveyListResponseDto;
import com.example.goENC.services.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@ResponseBody
@RequestMapping("/api/survey-info")
public class SurveyInfoController {

    @Autowired
    private final SurveyService surveyService;

    @GetMapping
    public List<SurveyListResponseDto> findAllSurveys() {
        return surveyService.findAll();
    }

    // 설문 ID에 따라 불러오기
    @GetMapping(value = "/{surveyId}")
    public SurveyListResponseDto findBySurveyId(@PathVariable Long surveyId) {
        return surveyService.findBySurveyId(surveyId);
    }

    // 설문 유저 ID에 따라 불러오기 (select by user ID...)
    @GetMapping(value = "/user/{userId}")
    public List<SurveyListResponseDto> findAllByUserId(@PathVariable Long userId) {
        return surveyService.findAllByUserId(userId);
    }
}
