package com.example.goENC.dto.survey.createSurvey;

import com.example.goENC.models.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class QuestionDto {
    private String questionTitle;
    private Integer questionOrder;
    private Integer questionType;
    private boolean[] questionOptions;
    private List<AnswerDto> questionAnswers;

    public QuestionDto(String questionTitle, Integer questionOrder, Integer questionType, boolean[] questionOptions, List<AnswerDto> questionAnswers) {
        this.questionTitle = questionTitle;
        this.questionOrder = questionOrder;
        this.questionType = questionType;
        this.questionOptions = questionOptions;
        this.questionAnswers = questionAnswers;
    }

    public QuestionDto(String questionTitle, Integer questionOrder, Integer questionType, boolean[] questionOptions) {
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