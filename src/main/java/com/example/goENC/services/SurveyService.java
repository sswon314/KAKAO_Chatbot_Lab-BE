package com.example.goENC.services;

import com.example.goENC.dto.survey.QuestionList;
import com.example.goENC.dto.survey.RequestCreateSurveyDto;
import com.example.goENC.models.Survey;
import com.example.goENC.repositories.QuestionRepository;
import com.example.goENC.repositories.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SurveyService {

    @Autowired
    SurveyRepository surveyRepository;

    @Autowired
    QuestionRepository questionRepository;

    public Integer createSurvey(RequestCreateSurveyDto requestDto) {
        Integer surveyId = surveyRepository.save(requestDto.toSurveyEntity()).getSurveyId();
        for (QuestionList question : requestDto.getQuestionCardList()) {
            System.out.println(questionRepository.save(question.toQuestionEntity()).getQuestionId());
        }

        return surveyId;
    }
}
