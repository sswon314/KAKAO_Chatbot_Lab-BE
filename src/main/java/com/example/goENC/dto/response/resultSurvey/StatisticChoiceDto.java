package com.example.goENC.dto.response.resultSurvey;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StatisticChoiceDto {

    private Integer surveyId;
    private Integer questionId;
    private Integer questionOrder;
    private Integer questionType;
    private String questionTitle;
    private Integer choiceAnswerId;
    private Integer answerOrder;
    private String answerContent;
    private Integer cnt;

    public StatisticChoiceDto(Integer survey_id, Integer question_id, Integer question_order, Integer question_type, String question_title, Integer choice_answer_id, Integer answer_order, String answer_content, Integer cnt){
        this.surveyId=survey_id;
        this.questionId=question_id;
        this.questionOrder=question_order;
        this.questionType=question_type;
        this.questionTitle=question_title;
        this.choiceAnswerId=choice_answer_id;
        this.answerOrder=answer_order;
        this.answerContent=answer_content;
        this.cnt=cnt;
    }
}
