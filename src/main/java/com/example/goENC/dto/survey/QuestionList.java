package com.example.goENC.dto.survey;

import com.example.goENC.models.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class QuestionList {
    private String questionTitle;
    private Integer questionOrder;
    private Integer questionType;
    private boolean[] questionOptions;
    private String[] questionAnswers;

    public QuestionList(String questionTitle, Integer questionOrder, Integer questionType, boolean[] questionOptions, String[] questionAnswers) {
        System.out.println("1");
        this.questionTitle = questionTitle;
        this.questionOrder = questionOrder;
        this.questionType = questionType;
        this.questionOptions = questionOptions;
        this.questionAnswers = questionAnswers;
    }

    public QuestionList(String questionTitle, Integer questionOrder, Integer questionType, boolean[] questionOptions) {
        System.out.println("2");
        this.questionTitle = questionTitle;
        this.questionOrder = questionOrder;
        this.questionType = questionType;
        this.questionOptions = questionOptions;
    }

    public Question toQuestionEntity() {
        if (this.questionType == 1) {
            return Question.builder()
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
                    .questionOrder(questionOrder)
                    .questionType(questionType)
                    .isRequire(questionOptions[0])
                    .build();
        }
    }
}