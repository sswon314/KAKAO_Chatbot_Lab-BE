package com.example.goENC.dto.response;

import com.example.goENC.models.ChoiceAnswer;
import com.example.goENC.models.Question;
import com.example.goENC.models.Survey;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ResponseSurveyDto {

    private Long surveyId = null;
    private String surveyTitle = null;
    private String surveyContent = null;
    private List<ResponseQuestionDto> questionCardList = null;

    public ResponseSurveyDto(Survey survey, List<Question> questionCardList, Map<Long, List<ChoiceAnswer>> choiceAnswerList) {
        this.surveyId = survey.getSurveyId();
        this.surveyTitle = survey.getSurveyTitle();
        this.surveyContent = survey.getSurveyDescription();

        this.questionCardList = questionCardList.stream().map(ResponseQuestionDto::new).collect(Collectors.toList());

        for(ResponseQuestionDto question:this.questionCardList){
            if(question.getQuestionType()==1){
                List<ResponseAnswerDto>answerDto=choiceAnswerList.get(question.getQuestionId()).stream().map(ResponseAnswerDto::new).collect(Collectors.toList());
                question.setQuestionAnswers(answerDto);
            }
        }
    }
}