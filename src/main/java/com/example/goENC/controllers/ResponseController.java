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

    @GetMapping("/{id}")
    public ResponseSurveyDto getSurveyTemplate(@PathVariable Integer id) {
        return responseService.getSurveyTemplate(id);
    }

    @PostMapping("/submit")
    public Integer submitSurvey(@RequestBody RequestSubmitSurveyDto requestDto) {
        return responseService.submitSurvey(requestDto);
    }

//     설문 ID에 따라 응답 통계 불러오기
    @GetMapping("/result/statistic/{id}")
    public SurveyStatisticDto getSurveyStatistic(@PathVariable Integer id) {
        return responseService.getSurveyStatistic(id);
    }
}
