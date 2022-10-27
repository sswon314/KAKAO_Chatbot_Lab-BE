package com.example.goENC.repositories;

import com.example.goENC.dto.response.resultSurvey.StatisticChoiceDto;
import com.example.goENC.dto.response.resultSurvey.StatisticSubjectiveDto;
import com.example.goENC.models.Question;
import com.example.goENC.models.Response;
import com.example.goENC.models.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.*;
import java.util.List;

public interface ResponseRepository extends JpaRepository<Response, Integer> {


    @Query(value = "select * from question where survey_id=:id", nativeQuery = true)
    List<Question> findQuestionList(@Param("id") Integer id);

    @Query(name="find_statistic_choice_dto", nativeQuery = true)
    List<StatisticChoiceDto> findStatisticChoice(@Param("id") Integer id);

    @Query(name="find_statistic_subjective_dto", nativeQuery = true)
    List<StatisticSubjectiveDto> findStatisticSubjective(@Param("id") Integer id);
}
