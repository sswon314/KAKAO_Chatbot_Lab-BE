package com.example.goENC.repositories;

import com.example.goENC.models.Survey;
import com.example.goENC.models.User;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveySingleRepository {
    Survey findBySurveyId(User userId, Survey surveyId);
}
