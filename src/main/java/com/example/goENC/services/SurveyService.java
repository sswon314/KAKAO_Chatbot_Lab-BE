package com.example.goENC.services;

import com.example.goENC.demoCode.models.Book;
import com.example.goENC.dto.SurveyListResponseDto;
import com.example.goENC.dto.SurveyUpdateDto;
import com.example.goENC.dto.response.ResponseSurveyDto;
import com.example.goENC.dto.survey.createSurvey.RequestChoiceAnswerDto;
import com.example.goENC.dto.survey.createSurvey.RequestQuestionDto;
import com.example.goENC.dto.survey.createSurvey.RequestCreateSurveyDto;
import com.example.goENC.models.ChoiceAnswer;
import com.example.goENC.models.Question;
import com.example.goENC.models.Survey;
import com.example.goENC.models.User;
import com.example.goENC.repositories.ChoiceAnswerRepository;
import com.example.goENC.repositories.QuestionRepository;
import com.example.goENC.repositories.SurveyRepository;
import com.example.goENC.repositories.SurveySingleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SurveyService {

    @Autowired
    SurveyRepository surveyRepository;


    SurveySingleRepository surveySingleRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    ChoiceAnswerRepository choiceAnswerRepository;

    @Transactional
    public Integer createSurvey(RequestCreateSurveyDto requestDto) {
        // 설문 DB에 저장 후 생성된 설문ID를 받아옴
        Survey surveyId = surveyRepository.save(requestDto.toSurveyEntity());

        // 질문배열 반복문 돌리면서 각 질문을 DB에 저장
        int questionOrder = 1;
        for (RequestQuestionDto question : requestDto.getQuestionCardList()) {
            Question questionId = questionRepository.save(question.toQuestionEntity(surveyId, questionOrder++));

            // 질문 유형이 객관식형이라면 답변 정보를 DB에 저장
            if (question.getQuestionType() == 1) {
                int answerOrder = 1;
                for (RequestChoiceAnswerDto answer : question.getQuestionAnswers()) {
                    choiceAnswerRepository.save(answer.toChoiceAnswerEntity(questionId, answerOrder++));
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
    public List<SurveyListResponseDto> findAllByUserId(Long userId) {
        User user = new User(userId);
        return surveyRepository.findAllByUserId(user).stream()
                .map(SurveyListResponseDto::new)
                .collect(Collectors.toList());
    }

    // 설문 ID와 유저 ID에 따라 불러오기 (select by survey ID)
    @Transactional(readOnly = true)
    public SurveyListResponseDto findBySurveyId(Long userId, Integer surveyId) {
        User user = new User(userId);
        Survey survey = new Survey(surveyId);
        return new SurveyListResponseDto(surveySingleRepository.findBySurveyId(user, survey));
    }

    // 설문 업데이트
    @Transactional
    public SurveyListResponseDto updateSurvey(Long userId, Integer surveyId,
                                              SurveyUpdateDto surveyUpdateDto) {
        User user = new User(userId);
        Survey surveyCheck = new Survey(surveyId);

        Survey survey = surveySingleRepository.findBySurveyId(user, surveyCheck);
        survey.updateSurvey(surveyUpdateDto.getSurveyStart(),
                surveyUpdateDto.getSurveyEnd(), surveyUpdateDto.getSurveyUrl());

        return null;
    }

    @Transactional
    @Query("DELETE FROM survey WHERE survey_id = {surveyId}")
    public Integer deleteFindBySurveyId(Integer surveyId) {
        Survey survey = surveyRepository.findSurveyBySurveyId(surveyId);
        List<Question> questionList = questionRepository.findQuestionList(surveyId);
        for (Question question : questionList) {
            List<ChoiceAnswer> choiceAnswerList = choiceAnswerRepository.findAnswerList(question.getQuestionId());
            for (ChoiceAnswer choiceAnswer : choiceAnswerList){
                choiceAnswerRepository.delete(choiceAnswer);
            }
            questionRepository.delete(question);
        }
        surveyRepository.delete(survey);
        return surveyId;
    }

    @Transactional
    public Integer copyBySurveyId(Integer originSurveyId) {
        // 설문 DB 에서 설문정보(by survey_id)를 받아온 후 설문 복제
        Survey originSurvey = surveyRepository.findSurveyBySurveyId(originSurveyId);
        Survey newSurvey = new Survey(originSurvey.getUserId(), originSurvey.getSurveyTitle(), originSurvey.getSurveyDescription());
        newSurvey = surveyRepository.save(newSurvey);

        // 질문 DB 에서 질문정보(by survey_id)를 받아온 후 질문 복제
        List<Question> originQuestionList = questionRepository.findQuestionList(originSurveyId);

        for (Question originQuestion : originQuestionList) {
            // map <원래 아이디, 새로 생성한 questionID)
            Map<Integer, Question> newQuestionListMap = new HashMap<>();
            Question newQuestion = new Question(
                    newSurvey,
                    originQuestion.getQuestionOrder(),
                    originQuestion.getQuestionTitle(),
                    originQuestion.getQuestionType(),
                    originQuestion.isRequire(),
                    originQuestion.isDuplicate(),
                    originQuestion.isMix()
            );
            newQuestionListMap.put(originQuestion.getQuestionId(), questionRepository.save(newQuestion));

            // 만약 객관식형이라면
            if (originQuestion.getQuestionType() == 1) {
                // 객관식유형 DB 에서 객관식유형정보(by question_id)를 받아온 후 질문 복제
                List<ChoiceAnswer> originChoiceAnswerList = choiceAnswerRepository.findAnswerList(originQuestion.getQuestionId());
                for (ChoiceAnswer originChoiceAnswer : originChoiceAnswerList) {
                    ChoiceAnswer newChoiceAnswer = new ChoiceAnswer(
                            newQuestionListMap.get(originQuestion.getQuestionId()), originChoiceAnswer.getAnswerOrder(), originChoiceAnswer.getAnswerContent()
                    );
                    choiceAnswerRepository.save(newChoiceAnswer);
                }
            }
        }
        // 설문 ID를 반환
        return newSurvey.getSurveyId();
    }
}
