package com.example.goENC.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "choice_answer")
@NoArgsConstructor
public class ChoiceAnswer {

    @Id  // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // SQL에서 auto_increment 의미
    @Column(name = "choice_answer_id", nullable = false)
    private Long choiceAnswerId;

    @ManyToOne
    @JoinColumn(name = "question_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Question questionId;
    // private Integer questionId;

    @Column(name = "answer_order")
    private Integer answerOrder;

    @Column(name = "answer_content", length = 50)
    private String answerContent;

    public ChoiceAnswer(Long answerId) {
        this.choiceAnswerId = answerId;
    }

    // 객관식 질문
    @Builder
    public ChoiceAnswer(Question questionId, Integer answerOrder, String answerContent) {
        this.questionId = questionId;
        this.answerOrder = answerOrder;
        this.answerContent = answerContent;
    }
}
