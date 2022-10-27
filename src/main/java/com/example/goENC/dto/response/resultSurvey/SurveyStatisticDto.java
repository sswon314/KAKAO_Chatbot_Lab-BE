package com.example.goENC.dto.response.resultSurvey;

import com.example.goENC.models.Survey;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@NoArgsConstructor
public class SurveyStatisticDto {
    // 1. surveyId
    // 2. surveyTitle
    // 3. surveyContent
    // 4. surveyTime { start, end }
    // 5. surveyStatistic []

    // select response.survey_id, choice_response.question_id, choice_answer_id, count(*) from response join choice_response on response.response_id=choice_response.response_id where survey_id=8 and question_id=23 group by choice_answer_id;
    // select response.survey_id, response.response_id, subjective_response.subjective_answer from response join subjective_response on response.response_id=subjective_response.response_id where survey_id=8;

    private Integer surveyId;
    private String surveyTitle;
    private String surveyContent;
    private SurveyTime surveyTime;
    private List<StatisticListDto> surveyStatistic;

    public SurveyStatisticDto(Survey survey, List<StatisticChoiceDto> statisticChoiceDtoList, List<StatisticSubjectiveDto> statisticSubjectiveDtoList) {
        this.surveyId = survey.getSurveyId();
        this.surveyTitle = survey.getSurveyTitle();
        this.surveyContent = survey.getSurveyDescription();
        this.surveyTime = new SurveyTime(survey.getSurveyStart(), survey.getSurveyEnd());

        this.surveyStatistic = new ArrayList<StatisticListDto>();
        for (StatisticChoiceDto choiceDto : statisticChoiceDtoList) {
            if (this.surveyStatistic.isEmpty() || this.surveyStatistic.get(this.surveyStatistic.size() - 1).getQuestionId()!=choiceDto.getQuestionId()){
                this.surveyStatistic.add(new StatisticListDto(choiceDto));
            }
            else{
                this.surveyStatistic.get(this.surveyStatistic.size() - 1).addAnswer(choiceDto);
            }
        }

        for (StatisticSubjectiveDto subjectiveDto : statisticSubjectiveDtoList) {
            // 여기서부터 시작해야함 : 저거 두개 dto로 surveyStatisticDto로 만들어야 함
            if (this.surveyStatistic.isEmpty() || this.surveyStatistic.get(this.surveyStatistic.size() - 1).getQuestionId() != subjectiveDto.getQuestionId()) {
                this.surveyStatistic.add(new StatisticListDto(subjectiveDto));
            } else {
                this.surveyStatistic.get(this.surveyStatistic.size() - 1).addAnswer(subjectiveDto);
            }
        }
    }

    public void sortList(){
        Collections.sort(this.surveyStatistic);

        for(StatisticListDto dto:this.surveyStatistic){
            if (dto.getType()==1) {
                dto.sortAnswer();
            }
        }
    }

    @Getter
    @NoArgsConstructor
    private class SurveyTime {
        private LocalDateTime start;
        private LocalDateTime end;

        public SurveyTime(LocalDateTime start, LocalDateTime end) {
            this.start = start;
            this.end = end;
        }
    }
}
