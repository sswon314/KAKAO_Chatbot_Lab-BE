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
    @GetMapping(value = "/surveys")
    public List<SurveyListResponseDto> findAllSurveys() {
        return surveyService.findAll();
    }

    // 설문 유저 ID에 따라 불러오기 (select by user ID...)
    @GetMapping(value = "userId={userId}")
    public List<SurveyListResponseDto> findAllByUserId(@PathVariable long userId) {
        return surveyService.findAllByUserId(userId);
    }

    // 설문 ID와 유저 ID에 따라 불러오기
    @GetMapping(value = "surveyId={userId}/{surveyId}")
    public SurveyListResponseDto findBySurveyId(@PathVariable("userId") long userId, @PathVariable("surveyId") int surveyId) {
        return surveyService.findBySurveyId(userId, surveyId);
    }

    @PutMapping(value = "/updateSurvey/{userId}/{surveyId}/")
    public SurveyListResponseDto updateSurvey(@PathVariable("userId") long userId, @PathVariable("surveyId") int surveyId,
                                              @RequestBody SurveyUpdateDto surveyUpdateDto) {
        return surveyService.updateSurvey(userId, surveyId, surveyUpdateDto);
    }

    @PutMapping(value = "/copy/surveyId={originSurveyId}")
    public Integer copyBySurveyId(@PathVariable Integer originSurveyId) {
        return surveyService.copyBySurveyId(originSurveyId);
    }

    @DeleteMapping(value = "/surveyId={surveyId}")
    public Integer deleteFindBySurveyId(@PathVariable Integer surveyId){
        return surveyService.deleteFindBySurveyId(surveyId);
    }
}
