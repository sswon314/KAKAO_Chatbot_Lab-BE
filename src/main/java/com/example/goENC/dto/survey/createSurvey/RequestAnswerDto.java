package com.example.goENC.dto.survey.createSurvey;

import com.example.goENC.models.ChoiceAnswer;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestAnswerDto {

    private Integer order;
    private String value;

    public RequestAnswerDto(Integer order, String value) {
        this.order = order;
        this.value = value;
    }

    public ChoiceAnswer toChoiceAnswerEntity(Integer questionId) {
        return ChoiceAnswer.builder()
                .questionId(questionId)
                .answerOrder(order)
                .answerContent(value)
                .build();
    }
}
