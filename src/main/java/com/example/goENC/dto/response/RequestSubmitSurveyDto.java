package com.example.goENC.dto.response;

import com.example.goENC.models.Response;
import com.example.goENC.models.Survey;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Getter
@NoArgsConstructor
public class RequestSubmitSurveyDto {

    private Survey surveyId = null;
    private String surveyTitle = null;
    private String surveyContent = null;
    private List<RequestSubmitAnswerDto> questionCardList = null;

    public RequestSubmitSurveyDto(Long surveyId, String surveyTitle, String surveyContent, List<RequestSubmitAnswerDto> questionCardList) {
        this.surveyId = new Survey(surveyId);
        this.surveyTitle = surveyTitle;
        this.surveyContent = surveyContent;
        this.questionCardList = questionCardList;
    }

    public Response toResponseEntity() {
        return Response.builder()
                .surveyId(surveyId)
                .responseTime(LocalDateTime.now(ZoneId.of("Asia/Seoul")))
                .build();
    }
}
