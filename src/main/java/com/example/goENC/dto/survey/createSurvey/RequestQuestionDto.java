package com.example.goENC.dto.survey.createSurvey;

import com.example.goENC.models.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@NoArgsConstructor
public class RequestQuestionDto {
    private String questionTitle;
    private Integer questionOrder;
    private Integer questionType;
    private boolean[] questionOptions;
    private List<RequestAnswerDto> questionAnswers;

    public RequestQuestionDto(String questionTitle, Integer questionOrder, Integer questionType, boolean[] questionOptions, List<RequestAnswerDto> questionAnswers) {
        this.questionTitle = questionTitle;
        this.questionOrder = questionOrder;
        this.questionType = questionType;
        this.questionOptions = questionOptions;
        this.questionAnswers = questionAnswers;
    }

    public RequestQuestionDto(String questionTitle, Integer questionOrder, Integer questionType, boolean[] questionOptions) {
        this.questionTitle = questionTitle;
        this.questionOrder = questionOrder;
        this.questionType = questionType;
        this.questionOptions = questionOptions;
    }


    public Question toQuestionEntity(Integer surveyId) {
        if (this.questionType == 1) {
            return Question.builder()
                    .surveyId(surveyId)
                    .questionTitle(questionTitle)
                    .questionOrder(questionOrder)
                    .questionType(questionType)
                    .isRequire(questionOptions[0])
                    .isDuplicate(questionOptions[1])
                    .isMix(questionOptions[2])
                    .build();
        } else {
            return Question.builder()
                    .questionTitle(questionTitle)
                    .surveyId(surveyId)
                    .questionOrder(questionOrder)
                    .questionType(questionType)
                    .isRequire(questionOptions[0])
                    .build();
        }
    }
}