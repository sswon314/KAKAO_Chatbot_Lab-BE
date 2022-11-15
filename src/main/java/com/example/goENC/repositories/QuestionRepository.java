package com.example.goENC.repositories;

import com.example.goENC.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query(value = "select * from question where survey_id=:id", nativeQuery = true)
    List<Question> findQuestionList(@Param("id") Long id);

    @Modifying
    @Query(value="delete from question where survey_id=:id", nativeQuery = true)
    void deleteQuestions(@Param("id") Long id);
}