package com.example.goENC.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "response")
@NoArgsConstructor
public class Response {

    @Id  // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // SQL에서 auto_increment 의미
    @Column(name = "response_id", nullable = false)
    private Integer responseId;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey surveyId;

    @Column(name = "response_time")
    private LocalDateTime responseTime;

    public Response(Integer responseId){
        this.responseId=responseId;
    }

    @Builder
    public Response(Survey surveyId, LocalDateTime responseTime) {
        this.surveyId = surveyId;
        this.responseTime = responseTime;
    }
}
