package com.example.goENC.dto.survey;

import com.example.goENC.demoCode.models.Book;
import com.example.goENC.models.Question;
import com.example.goENC.models.Survey;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RequestCreateSurveyDto {
    private Integer userId = null;
    private String surveyTitle = null;
    private String surveyContent = null;
    private List<QuestionList> questionCardList = null;

    public RequestCreateSurveyDto(Integer userId, String surveyTitle, String surveyContent, List<QuestionList> questionCardList) {
        this.userId = userId;
        this.surveyTitle = surveyTitle;
        this.surveyContent = surveyContent;
        this.questionCardList = questionCardList;
    }

    public Survey toSurveyEntity() {
        System.out.println(this.questionCardList);
        return Survey.builder()
                .userId(userId)
                .surveyTitle(surveyTitle)
                .surveyDescription(surveyContent)
                .build();
    }
}