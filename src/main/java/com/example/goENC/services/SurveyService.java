package com.example.goENC.services;

import com.example.goENC.dto.SurveyListResponseDto;
import com.example.goENC.dto.survey.createSurvey.RequestAnswerDto;
import com.example.goENC.dto.survey.createSurvey.RequestQuestionDto;
import com.example.goENC.dto.survey.createSurvey.RequestCreateSurveyDto;
import com.example.goENC.models.Question;
import com.example.goENC.models.Survey;
import com.example.goENC.models.User;
import com.example.goENC.repositories.ChoiceAnswerRepository;
import com.example.goENC.repositories.QuestionRepository;
import com.example.goENC.repositories.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SurveyService {

    @Autowired
    SurveyRepository surveyRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    ChoiceAnswerRepository choiceAnswerRepository;

    @Transactional
    public Integer createSurvey(RequestCreateSurveyDto requestDto) {
        // 설문 DB에 저장 후 생성된 설문ID를 받아옴
        Survey surveyId = surveyRepository.save(requestDto.toSurveyEntity());

        // 질문배열 반복문 돌리면서 각 질문을 DB에 저장
        for (RequestQuestionDto question : requestDto.getQuestionCardList()) {
            Question questionId = questionRepository.save(question.toQuestionEntity(surveyId));

            // 질문 유형이 객관식형이라면 답변 정보를 DB에 저장
            if (question.getQuestionType() == 1) {
                for (RequestAnswerDto answer : question.getQuestionAnswers()) {
                    choiceAnswerRepository.save(answer.toChoiceAnswerEntity(questionId));
                }
            }
        }

        // 설문 ID를 반환
        return surveyId.getSurveyId();
    }

    // 설문 전체 불러오기 (select *)
    @Transactional(readOnly = true) // 트랜젝션을 읽기 전용으로 함
    public List<SurveyListResponseDto> findAll() {
        return surveyRepository.findAll().stream()
                .map(SurveyListResponseDto::new)
                .collect(Collectors.toList());
    }

    // 설문 유저 ID에 따라 불러오기 (select by user ID...)
    @Transactional(readOnly = true) // 트랜젝션을 읽기 전용으로 함
    @Query("SELECT * FROM SURVEY WHERE user_id = 1")
    public List<SurveyListResponseDto> findAllByUserId(Integer userId) {
        User user=new User(userId);
        return surveyRepository.findAllByUserId(user).stream()
                .map(SurveyListResponseDto::new)
                .collect(Collectors.toList());
    }

    // 설문 ID에 따라 불러오기 (select by survey ID)
    @Transactional(readOnly = true)
    @Query("SELECT * FROM SURVEY WHERE user_id = 1")
    public SurveyListResponseDto findBySurveyId(Integer surveyId) {
        Survey survey=new Survey(surveyId);
        return new SurveyListResponseDto(surveyRepository.findBySurveyId(survey));
    }
}
