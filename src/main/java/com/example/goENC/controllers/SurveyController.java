package com.example.goENC.controllers;

import com.example.goENC.dto.SurveyListResponseDto;
import com.example.goENC.dto.SurveyUpdateDto;
import com.example.goENC.dto.survey.createSurvey.RequestCreateSurveyDto;
import com.example.goENC.dto.survey.reviseSurvey.RequestReviseSurveyDto;
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

    @PostMapping()
    public Long createSurvey(@RequestBody RequestCreateSurveyDto requestDto) {
        return surveyService.createSurvey(requestDto);
    }

    @GetMapping()
    public List<SurveyListResponseDto> findAllSurveys() {
        return surveyService.findAll();
    }

    // 설문 유저 ID에 따라 불러오기 (select by user ID...)
    @GetMapping(value = "/user/{userId}")
    public List<SurveyListResponseDto> findAllByUserId(@PathVariable Long userId) {
        return surveyService.findAllByUserId(userId);
    }

    // 설문 ID에 따라 불러오기
    @GetMapping(value = "/{surveyId}")
    public SurveyListResponseDto findBySurveyId(@PathVariable Long surveyId) {
        return surveyService.findBySurveyId(surveyId);
    }

    // 설문 정보 업데이트하기
    @PutMapping(value = "/{surveyId}/meta")
    public SurveyListResponseDto updateSurvey(@PathVariable Long surveyId, @RequestBody SurveyUpdateDto surveyUpdateDto) {
        return surveyService.updateSurvey(surveyId, surveyUpdateDto);
    }

    @PutMapping(value = "/{surveyId}/duplicate")
    public Long copyBySurveyId(@PathVariable Long surveyId) {
        return surveyService.copyBySurveyId(surveyId);
    }

    @PutMapping(value = "/{surveyId}/template")
    public Long reviseSurvey(@PathVariable Long surveyId, @RequestBody RequestReviseSurveyDto requestDto) {
        return surveyService.reviseSurvey(surveyId, requestDto);
    }

    @DeleteMapping(value = "/{surveyId}")
    public Long deleteFindBySurveyId(@PathVariable Long surveyId) {
        return surveyService.deleteFindBySurveyId(surveyId);
    }
}
