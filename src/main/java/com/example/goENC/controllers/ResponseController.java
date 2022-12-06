package com.example.goENC.controllers;

import com.example.goENC.dto.response.ResponseSurveyDto;
import com.example.goENC.dto.response.RequestSubmitSurveyDto;
import com.example.goENC.dto.response.resultSurvey.SurveyStatisticDto;
import com.example.goENC.services.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@ResponseBody
@RequestMapping("/api/response")
public class ResponseController {

    @Autowired
    private final ResponseService responseService;

    @GetMapping(value = "/{surveyId}")
    public ResponseSurveyDto getSurveyTemplate(@PathVariable Long surveyId) {
        return responseService.getSurveyTemplate(surveyId);
    }

    @PostMapping()
    public Long submitSurvey(@RequestBody RequestSubmitSurveyDto requestDto) {
        return responseService.submitSurvey(requestDto);
    }

    //     설문 ID에 따라 응답 통계 불러오기
    @GetMapping(value="/statistic/{surveyId}")
    public SurveyStatisticDto getSurveyStatistic(@PathVariable Long surveyId) {
        return responseService.getSurveyStatistic(surveyId);
    }
}
