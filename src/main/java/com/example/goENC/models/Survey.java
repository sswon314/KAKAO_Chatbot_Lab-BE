package com.example.goENC.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "survey")
@NoArgsConstructor
public class Survey {

    @Id  // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // SQL에서 auto_increment 의미
    @Column(name = "survey_id", nullable = false)
    private Long surveyId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User userId;
    // private Integer userId;

    @Column(name = "survey_title", length = 50)
    private String surveyTitle;

    @Column(name = "survey_description", length = 100)
    private String surveyDescription;

    @Column(name = "survey_start")
    private LocalDateTime surveyStart;

    @Column(name = "survey_end")
    private LocalDateTime surveyEnd;

    @Column(name = "survey_url", length = 100)
    private String surveyUrl;

    public Survey(Long surveyId) {
        this.surveyId = surveyId;
    }

    @Builder
    public Survey(User userId, String surveyTitle, String surveyDescription) {
        this.userId = userId;
        this.surveyTitle = surveyTitle;
        this.surveyDescription = surveyDescription;
    }

    // 시작일 종료일 설정시 링크 생성
    public void updateSurvey(LocalDateTime surveyStart, LocalDateTime surveyEnd, String surveyUrl) {
        this.surveyStart = surveyStart;
        this.surveyEnd = surveyEnd;
        this.surveyUrl = surveyUrl;
    }

    public void update(String newTitle, String newContent) {
        this.surveyTitle = newTitle;
        this.surveyDescription = newContent;
    }
}
