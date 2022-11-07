package com.example.goENC.dto.response;

import com.example.goENC.models.Question;
import com.example.goENC.models.Survey;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class ResponseQuestionDto {
    private Long questionId;
    private String questionTitle;
    private Integer questionOrder;
    private Integer questionType;
    private boolean[] questionOptions;
    private List<ResponseAnswerDto> questionAnswers;

    public ResponseQuestionDto(Question question) {
        this.questionId=question.getQuestionId();
        this.questionTitle = question.getQuestionTitle();
        this.questionOrder = question.getQuestionOrder();
        this.questionType = question.getQuestionType();

        if (this.questionType == 1) {
            this.questionOptions = new boolean[]{question.isRequire(), question.isDuplicate(), question.isMix()};
        } else {
            this.questionOptions = new boolean[]{question.isRequire()};
        }
    }


    public Question toQuestionEntity(Survey surveyId) {
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