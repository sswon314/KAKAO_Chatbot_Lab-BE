package com.example.goENC.controllers;

import com.example.goENC.dto.SurveyListResponseDto;
import com.example.goENC.dto.survey.createSurvey.RequestCreateSurveyDto;
import com.example.goENC.services.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // 설문 전체 불러오기 (select *)
    @GetMapping(value = "/surveys")
    public List<SurveyListResponseDto> findAllSurveys() {
        return surveyService.findAll();
    }

    // 설문 유저 ID에 따라 불러오기 (select by user ID...)
    @GetMapping(value = "userId={userId}")
    public List<SurveyListResponseDto> findAllByUserId(@PathVariable int userId) {
        return surveyService.findAllByUserId(userId);
    }

    // 설문 ID에 따라 불러오기 (select by survey ID)
    @GetMapping(value = "surveyId={surveyId}")
    public SurveyListResponseDto findBySurveyId(@PathVariable int surveyId) {
        return surveyService.findBySurveyId(surveyId);
    }
}
