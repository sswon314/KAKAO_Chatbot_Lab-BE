package com.example.goENC.dto.response;

import com.example.goENC.models.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class RequestSubmitAnswerDto {

    private Question questionId;
    private Integer questionOrder;
    private Integer questionType;
    private String questionTitle;
    private boolean[] questionOptions;

    private List<ChoiceResponseDto> questionAnswers;
    private String questionAnswer;

    public RequestSubmitAnswerDto(Long questionId, Integer questionOrder, Integer questionType, String questionTitle, boolean[] questionOptions, List<ChoiceResponseDto> questionAnswers) {
        this.questionId = new Question(questionId);
        this.questionOrder = questionOrder;
        this.questionType = questionType;
        this.questionTitle = questionTitle;
        this.questionOptions=questionOptions;
        this.questionAnswers=questionAnswers;
    }

    public RequestSubmitAnswerDto(Long questionId, Integer questionOrder, Integer questionType, String questionTitle, boolean[] questionOptions, String questionAnswer) {
        this.questionId = new Question(questionId);
        this.questionOrder = questionOrder;
        this.questionType = questionType;
        this.questionTitle = questionTitle;
        this.questionOptions=questionOptions;
        this.questionAnswer=questionAnswer;
    }

    public List<ChoiceResponse> toChoiceResponseEntity(Response responseId) {
        List<ChoiceResponse> choiceResponseList = new ArrayList<>();
        for (ChoiceResponseDto answer : questionAnswers) {
            if (answer.getIsCheck()) {
                choiceResponseList.add(
                        ChoiceResponse.builder()
                        .questionId(questionId)
                        .responseId(responseId)
                        .choiceAnswerId(new ChoiceAnswer(answer.getId()))
                        .build());
            }
        }

        return choiceResponseList;
    }

    public SubjectiveResponse toSubjectiveResponseEntity(Response responseId) {
        return SubjectiveResponse.builder()
                .questionId(questionId)
                .responseId(responseId)
                .subjectiveAnswer(questionAnswer)
                .build();
    }
}