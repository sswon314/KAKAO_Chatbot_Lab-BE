package com.example.goENC.services;

import com.example.goENC.dto.response.ResponseSurveyDto;
import com.example.goENC.dto.response.RequestSubmitAnswerDto;
import com.example.goENC.dto.response.RequestSubmitSurveyDto;
import com.example.goENC.dto.response.resultSurvey.StatisticChoiceDto;
import com.example.goENC.dto.response.resultSurvey.StatisticSubjectiveDto;
import com.example.goENC.dto.response.resultSurvey.SurveyStatisticDto;
import com.example.goENC.models.*;
import com.example.goENC.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ResponseService {

    @Autowired
    SurveyRepository surveyRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    ChoiceAnswerRepository choiceAnswerRepository;

    @Autowired
    ResponseRepository responseRepository;

    @Autowired
    ChoiceResponseRepository choiceResponseRepository;

    @Autowired
    SubjectiveResponseRepository subjectiveResponseRepository;

    @Transactional(readOnly = true)
    public ResponseSurveyDto getSurveyTemplate(Integer id) {
        // 설문id에 대한 설문 table의 정보 가져옴
        Survey survey = surveyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 설문이 없습니다. id=" + id));

        // 설문id에 대한 질문 table의 정보리스트 가져옴
        List<Question> questionList = questionRepository.findQuestionList(id);

        // 객관식형 질문에 대한 답변 table의 정보리스트 가져옴
        Map<Integer, List<ChoiceAnswer>> choiceAnswerList = new HashMap<>();
        for (Question question : questionList) {
            if (question.getQuestionType() == 1) {
                Integer questionId = question.getQuestionId();
                choiceAnswerList.put(questionId, choiceAnswerRepository.findAnswerList(questionId));
            }
        }

        return new ResponseSurveyDto(survey, questionList, choiceAnswerList);
    }

    @Transactional
    public Integer submitSurvey(RequestSubmitSurveyDto requestDto) {
        Response responseId = responseRepository.save(requestDto.toResponseEntity());

        for (RequestSubmitAnswerDto answer : requestDto.getQuestionCardList()) {
            if (answer.getQuestionType() == 1) {
                for(ChoiceResponse response:answer.toChoiceResponseEntity(responseId)){
                    choiceResponseRepository.save(response);
                }

            } else {
                subjectiveResponseRepository.save(answer.toSubjectiveResponseEntity(responseId));
            }
        }

        // 응답 ID를 반환
        return responseId.getResponseId();
    }

    @Transactional(readOnly = true)
    public SurveyStatisticDto getSurveyStatistic(Integer surveyId) {
        // 1. surveyId
        // 2. surveyTitle
        // 3. surveyContent
        // 4. surveyTime { start, end }
        // 5. surveyStatistic []
        // 5.1 객관식{questionId, questionOrder, type, title, answers[{answerId, answerOrder, value, cnt}]}
        // 5.2 주관식{questionId, questionOrder, type, title, answers[{value}]

        // step.1 설문 아이디를 통해 설문 정보 가져옴 (1,2,3,4 정보 획득)
        // step.2 설문 아이디에 해당하는 response
        // 설문id에 대한 설문 table의 정보 가져옴
        Survey survey = surveyRepository.findById(surveyId).orElseThrow(() -> new IllegalArgumentException("해당 설문이 없습니다. id=" + surveyId));
        List<StatisticChoiceDto> statisticChoiceDtoList=responseRepository.findStatisticChoice(surveyId);
        List<StatisticSubjectiveDto>statisticSubjectiveDtoList=responseRepository.findStatisticSubjective(surveyId);

        SurveyStatisticDto surveyStatisticDto=new SurveyStatisticDto(survey,statisticChoiceDtoList,statisticSubjectiveDtoList);
        surveyStatisticDto.sortList();

        return surveyStatisticDto;
    }
}