package com.example.goENC.dto.survey.reviseSurvey;

import com.example.goENC.dto.survey.createSurvey.RequestQuestionDto;
import com.example.goENC.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RequestReviseSurveyDto {
    private User userId = null;
    private Integer surveyId = null;
    private String surveyTitle = null;
    private String surveyContent = null;
    private List<RequestQuestionDto> questionCardList = null;

    public RequestReviseSurveyDto(Long userId, Integer surveyId, String surveyTitle, String surveyContent, List<RequestQuestionDto> questionCardList) {
        this.userId = new User(userId);
        this.surveyId = surveyId;
        this.surveyTitle = surveyTitle;
        this.surveyContent = surveyContent;
        this.questionCardList = questionCardList;
    }
}
