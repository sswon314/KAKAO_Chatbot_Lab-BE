package com.example.goENC.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class SurveyUpdateDto {

    private LocalDateTime surveyStart; // 설문 시작일

    private LocalDateTime surveyEnd; // 설문 종료일

    private String surveyUrl; // 설문 링크

    @Builder
    public SurveyUpdateDto(LocalDateTime surveyStart,
                           LocalDateTime surveyEnd, String surveyUrl) {
        this.surveyStart = surveyStart;
        this.surveyEnd = surveyEnd;
        this.surveyUrl = surveyUrl;
    }
}
