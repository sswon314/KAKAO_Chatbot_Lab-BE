package com.example.goENC.controllers;

import com.example.goENC.dto.response.resultSurvey.SurveyStatisticDto;
import com.example.goENC.services.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@ResponseBody
@RequestMapping("/api/statistic")
public class StatisticController {

    @Autowired
    private final ResponseService responseService;

    //     설문 ID에 따라 응답 통계 불러오기
    @GetMapping("/{surveyId}")
    public SurveyStatisticDto getSurveyStatistic(@PathVariable Long surveyId) {
        return responseService.getSurveyStatistic(surveyId);
    }
}
