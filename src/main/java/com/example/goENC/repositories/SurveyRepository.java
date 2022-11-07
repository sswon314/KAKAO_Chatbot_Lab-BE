package com.example.goENC.repositories;

import com.example.goENC.models.Survey;
import com.example.goENC.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface SurveyRepository extends JpaRepository<Survey, Long> {
    @Override
    List<Survey> findAll();

    List<Survey> findAllByUserId(User userId);

    Survey findSurveyBySurveyId(Long surveyId);

    @Query(value = "select * from survey where survey_id=:id", nativeQuery = true)
    Survey findUserBySurveyId(@Param("id") Long id);
}
