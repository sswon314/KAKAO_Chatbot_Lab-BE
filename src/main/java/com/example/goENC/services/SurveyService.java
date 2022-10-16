package com.example.goENC.services;

import com.example.goENC.dto.survey.createSurvey.AnswerDto;
import com.example.goENC.dto.survey.createSurvey.QuestionDto;
import com.example.goENC.dto.survey.createSurvey.RequestCreateSurveyDto;
import com.example.goENC.repositories.ChoiceAnswerRepository;
import com.example.goENC.repositories.QuestionRepository;
import com.example.goENC.repositories.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SurveyService {

    @Autowired
    SurveyRepository surveyRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    ChoiceAnswerRepository choiceAnswerRepository;

    public Integer createSurvey(RequestCreateSurveyDto requestDto) {
        // 설문 DB에 저장 후 생성된 설문ID를 받아옴
        Integer surveyId = surveyRepository.save(requestDto.toSurveyEntity()).getSurveyId();

        // 질문배열 반복문 돌리면서 각 질문을 DB에 저장
        for (QuestionDto question : requestDto.getQuestionCardList()) {
            Integer questionId = questionRepository.save(question.toQuestionEntity(surveyId)).getQuestionId();

            if (question.getQuestionType() == 1) {
                for(AnswerDto answer: question.getQuestionAnswers()){
                    choiceAnswerRepository.save(answer.toChoiceAnswerEntity(questionId));
                }
            }
        }

        // 설문 ID를 반환
        return surveyId;
    }
}
