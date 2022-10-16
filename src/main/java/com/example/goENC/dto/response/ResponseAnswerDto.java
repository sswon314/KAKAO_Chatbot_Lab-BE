package com.example.goENC.dto.response;

import com.example.goENC.models.ChoiceAnswer;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseAnswerDto {

    private Integer id;
    private Integer order;
    private String value;

    public ResponseAnswerDto(ChoiceAnswer choiceAnswer) {
        this.id=choiceAnswer.getAnswerId();
        this.order = choiceAnswer.getAnswerOrder();
        this.value = choiceAnswer.getAnswerContent();
    }
}
