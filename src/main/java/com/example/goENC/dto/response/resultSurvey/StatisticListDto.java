package com.example.goENC.dto.response.resultSurvey;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
@NoArgsConstructor
public class StatisticListDto implements Comparable<StatisticListDto> {
    // 5.1 객관식{questionId, questionOrder, type, title, answers[{answerId, answerOrder, value, cnt}]}
    // 5.2 주관식{questionId, questionOrder, type, title, answers[{value}]

    private Long questionId;
    private Integer questionOrder;
    private Integer type;
    private String title;
    private List<Answer> answers = new ArrayList<>();

    // 객관식 통계
    public StatisticListDto(StatisticChoiceDto statisticChoiceDto) {
        this.questionId = statisticChoiceDto.getQuestionId();
        this.questionOrder = statisticChoiceDto.getQuestionOrder();
        this.type = statisticChoiceDto.getQuestionType();
        this.title = statisticChoiceDto.getQuestionTitle();
        this.answers.add(new Answer(statisticChoiceDto.getChoiceAnswerId(), statisticChoiceDto.getAnswerOrder(), statisticChoiceDto.getAnswerContent(), statisticChoiceDto.getCnt()));
    }

    // 주관식 통계
    public StatisticListDto(StatisticSubjectiveDto statisticSubjectiveDto) {
        this.questionId = statisticSubjectiveDto.getQuestionId();
        this.questionOrder = statisticSubjectiveDto.getQuestionOrder();
        this.type = statisticSubjectiveDto.getQuestionType();
        this.title = statisticSubjectiveDto.getQuestionTitle();
        this.answers.add(new Answer(statisticSubjectiveDto.getAnswerContent()));
    }

    // 객관식 질문 통계의 답변 통계 추가
    public void addAnswer(StatisticChoiceDto statisticChoiceDto) {
        this.answers.add(new Answer(statisticChoiceDto.getChoiceAnswerId(), statisticChoiceDto.getAnswerOrder(), statisticChoiceDto.getAnswerContent(), statisticChoiceDto.getCnt()));
    }

    // 주관식 질문 통계의 답변 통계 추가
    public void addAnswer(StatisticSubjectiveDto statisticSubjectiveDto) {
        this.answers.add(new Answer(statisticSubjectiveDto.getAnswerContent()));
    }

    public void sortAnswer(){
        Collections.sort(this.answers);
    }

    @Override
    public int compareTo(StatisticListDto o) {
        return this.questionOrder-o.getQuestionOrder();
    }

    @Getter
    @NoArgsConstructor
    private class Answer implements Comparable<Answer>{
        private Long answerId;
        private Integer answerOrder;
        private String value;
        private Integer cnt;

        public Answer(Long answerId, Integer answerOrder, String value, Integer cnt) {
            this.answerId = answerId;
            this.answerOrder = answerOrder;
            this.value = value;
            this.cnt = cnt;
        }

        public Answer(String value) {
            this.value = value;
        }

        @Override
        public int compareTo(Answer o) {
            return this.answerOrder - o.getAnswerOrder();
        }
    }
}
