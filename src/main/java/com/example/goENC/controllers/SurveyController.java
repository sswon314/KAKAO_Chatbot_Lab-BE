package com.example.goENC.controllers;

import com.example.goENC.dto.SurveyListResponseDto;
import com.example.goENC.dto.SurveyUpdateDto;
import com.example.goENC.dto.survey.createSurvey.RequestCreateSurveyDto;
import com.example.goENC.models.Survey;
import com.example.goENC.services.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
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
    @GetMapping
    public List<SurveyListResponseDto> findAllSurveys() {
        return surveyService.findAll();
    }

    // 설문 유저 ID에 따라 불러오기 (select by user ID...)
    @GetMapping
    public List<SurveyListResponseDto> findAllByUserId(@RequestParam(name = "user-id") Long userId) {
        return surveyService.findAllByUserId(userId);
    }

    // 설문 ID에 따라 불러오기
    @GetMapping
    public SurveyListResponseDto findBySurveyId(@RequestParam(name = "survey-id") Integer surveyId) {
        return surveyService.findBySurveyId(surveyId);
    }

    // 설문 정보 업데이트하기
    @PutMapping
    public SurveyListResponseDto updateSurvey(@RequestParam(name = "survey-id") Integer surveyId, @RequestBody SurveyUpdateDto surveyUpdateDto) {
        return surveyService.updateSurvey(surveyId, surveyUpdateDto);
    }

    @PutMapping
    public Integer copyBySurveyId(@RequestParam(name = "origin-id") Integer originSurveyId) {
        return surveyService.copyBySurveyId(originSurveyId);
    }

    @DeleteMapping
    public Integer deleteFindBySurveyId(@PathVariable Integer surveyId) {
        return surveyService.deleteFindBySurveyId(surveyId);
    }
}
