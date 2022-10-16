package com.example.goENC.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "question")
@NoArgsConstructor
public class Question {

    @Id  // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // SQL에서 auto_increment 의미
    @Column(name = "question_id", nullable = false)
    private Integer questionId;

    @JoinColumn(name = "survey_id")
    private Integer surveyId;

    @Column(name = "question_order")
    private Integer questionOrder;

    @Column(name = "question_title", length = 50)
    private String questionTitle;

    @Column(name = "question_type")
    private Integer questionType;

    @Column(name = "is_require")
    private boolean isRequire;

    @Column(name = "is_duplicate")
    private boolean isDuplicate;

    @Column(name = "is_mix")
    private boolean isMix;

    // 객관식 질문
    @Builder
    public Question(Integer questionId, Integer surveyId, Integer questionOrder, String questionTitle, Integer questionType, boolean isRequire, boolean isDuplicate, boolean isMix) {
        this.questionId = questionId;
        this.surveyId = surveyId;
        this.questionOrder = questionOrder;
        this.questionTitle = questionTitle;
        this.questionType = questionType;
        this.isRequire = isRequire;
        this.isDuplicate = isDuplicate;
        this.isMix = isMix;
    }

    // 주관식 질문
    @Builder
    public Question(Integer questionId, Integer surveyId, Integer questionOrder, String questionTitle, Integer questionType, boolean isRequire) {
        this.questionId = questionId;
        this.surveyId = surveyId;
        this.questionOrder = questionOrder;
        this.questionTitle = questionTitle;
        this.questionType = questionType;
        this.isRequire = isRequire;
    }
}