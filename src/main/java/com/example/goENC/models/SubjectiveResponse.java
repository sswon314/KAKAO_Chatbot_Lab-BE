package com.example.goENC.models;

import com.example.goENC.models.compositeKey.SubjectiveResponseId;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "subjective_response")
@IdClass(SubjectiveResponseId.class)
@NoArgsConstructor
public class SubjectiveResponse {

    @Id  // Primary key
    @ManyToOne
    @JoinColumn(name = "question_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Question questionId;

    @Id  // Primary key
    @ManyToOne
    @JoinColumn(name = "response_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Response responseId;

    @Column(name = "subjective_answer", length = 100)
    private String subjectiveAnswer;

    // 객관식 질문
    @Builder
    public SubjectiveResponse(Question questionId, Response responseId, String subjectiveAnswer) {
        this.questionId = questionId;
        this.responseId = responseId;
        this.subjectiveAnswer = subjectiveAnswer;
    }
}
