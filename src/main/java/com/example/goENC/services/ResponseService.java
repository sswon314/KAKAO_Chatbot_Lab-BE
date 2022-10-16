package com.example.goENC.services;

import com.example.goENC.dto.response.ResponseSurveyDto;
import com.example.goENC.models.ChoiceAnswer;
import com.example.goENC.models.Question;
import com.example.goENC.models.Survey;
import com.example.goENC.repositories.ChoiceAnswerRepository;
import com.example.goENC.repositories.QuestionRepository;
import com.example.goENC.repositories.SurveyRepository;
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
}
