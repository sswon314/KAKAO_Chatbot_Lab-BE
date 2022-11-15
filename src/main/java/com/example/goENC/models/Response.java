package com.example.goENC.models;

import com.example.goENC.dto.response.resultSurvey.StatisticChoiceDto;
import com.example.goENC.dto.response.resultSurvey.StatisticSubjectiveDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NamedNativeQuery(
        name = "find_statistic_choice_dto",
        query = "select r.survey_id, q.question_id, q.question_order, q.question_type, q.question_title, ca.choice_answer_id, ca.answer_order, ca.answer_content, count(*) as cnt " +
                "from response as r, choice_response as cr, choice_answer as ca, question as q " +
                "where r.response_id=cr.response_id and cr.choice_answer_id=ca.choice_answer_id and ca.question_id=q.question_id and r.survey_id=:id " +
                "group by ca.choice_answer_id,q.question_id " +
                "order by q.question_id;",
        resultSetMapping = "statistic_choice_dto"
)
@SqlResultSetMapping(
        name = "statistic_choice_dto",
        classes = @ConstructorResult(
                targetClass = StatisticChoiceDto.class,
                columns = {
                        @ColumnResult(name = "survey_id", type = Long.class),
                        @ColumnResult(name = "question_id", type = Long.class),
                        @ColumnResult(name = "question_order", type = Integer.class),
                        @ColumnResult(name = "question_type", type = Integer.class),
                        @ColumnResult(name = "question_title", type = String.class),
                        @ColumnResult(name = "choice_answer_id", type = Long.class),
                        @ColumnResult(name = "answer_order", type = Integer.class),
                        @ColumnResult(name = "answer_content", type = String.class),
                        @ColumnResult(name = "cnt", type = Integer.class),
                }
        )
)
@NamedNativeQuery(
        name = "find_statistic_subjective_dto",
        query = "select r.survey_id, q.question_id, q.question_order, q.question_type, q.question_title, sr.subjective_answer " +
                "from response as r, subjective_response as sr, question as q " +
                "where r.response_id=sr.response_id and sr.question_id=q.question_id and r.survey_id=:id " +
                "order by q.question_id;",
        resultSetMapping = "statistic_subjective_dto"
)
@SqlResultSetMapping(
        name = "statistic_subjective_dto",
        classes = @ConstructorResult(
                targetClass = StatisticSubjectiveDto.class,
                columns = {
                        @ColumnResult(name = "survey_id", type = Long.class),
                        @ColumnResult(name = "question_id", type = Long.class),
                        @ColumnResult(name = "question_order", type = Integer.class),
                        @ColumnResult(name = "question_type", type = Integer.class),
                        @ColumnResult(name = "question_title", type = String.class),
                        @ColumnResult(name = "subjective_answer", type = String.class)
                }
        )
)
@Getter
@Table(name = "response")
@NoArgsConstructor
public class Response {

    @Id  // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // SQL에서 auto_increment 의미
    @Column(name = "response_id", nullable = false)
    private Long responseId;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Survey surveyId;

    @Column(name = "response_time")
    private LocalDateTime responseTime;

    public Response(Long responseId){
        this.responseId=responseId;
    }

    @Builder
    public Response(Survey surveyId, LocalDateTime responseTime) {
        this.surveyId = surveyId;
        this.responseTime = responseTime;
    }
}
