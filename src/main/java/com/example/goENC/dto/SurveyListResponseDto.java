package com.example.goENC.dto;

import com.example.goENC.models.Survey;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SurveyListResponseDto {

    private Long surveyId; // 설문 ID
    private Long userId; // 유저 ID
    private String surveyTitle; // 설문 제목
    private String surveyDescription; // 설문 부연 설명
    private LocalDateTime surveyStart; // 설문 시작일
    private LocalDateTime surveyEnd; // 설문 종료일
    private String surveyUrl; // 설문 링크

    public SurveyListResponseDto(Survey entity) {
        this.surveyId = entity.getSurveyId();
        this.userId = entity.getUserId().getUserId();
        this.surveyTitle = entity.getSurveyTitle();
        this.surveyDescription = entity.getSurveyDescription();
        this.surveyStart = entity.getSurveyStart();
        this.surveyEnd = entity.getSurveyEnd();
        this.surveyUrl = entity.getSurveyUrl();
    }

    @Override
    public String toString(){
        return "Survey [surveyId=" + surveyId + ", userId=" + userId + ", surveyTitle=" + surveyTitle + "]";
    }
}