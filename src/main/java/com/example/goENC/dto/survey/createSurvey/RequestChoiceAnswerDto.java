package com.example.goENC.dto.survey.createSurvey;

import com.example.goENC.models.ChoiceAnswer;
import com.example.goENC.models.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestChoiceAnswerDto {

    private String value;

    public RequestChoiceAnswerDto(String value) {
        this.value = value;
    }

    public ChoiceAnswer toChoiceAnswerEntity(Question questionId,Integer answerOrder) {
        return ChoiceAnswer.builder()
                .questionId(questionId)
                .answerOrder(answerOrder)
                .answerContent(value)
                .build();
    }
}
