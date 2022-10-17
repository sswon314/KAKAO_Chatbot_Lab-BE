package com.example.goENC.models.compositeKey;

import com.example.goENC.models.ChoiceAnswer;
import com.example.goENC.models.Question;
import com.example.goENC.models.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChoiceResponseId implements Serializable {
    private Integer questionId;
    private Integer responseId;
    private Integer choiceAnswerId;
}
