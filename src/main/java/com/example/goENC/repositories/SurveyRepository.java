package com.example.goENC.repositories;

import com.example.goENC.models.Survey;
import com.example.goENC.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SurveyRepository extends JpaRepository<Survey, Integer> {
    @Override
    List<Survey> findAll();

    List<Survey> findAllByUserId(User userId);

    Survey findBySurveyId(Survey surveyId);
}
