package dev.group.cybershield.quiz.model;

import lombok.Data;

import java.util.List;

@Data
public class SubmitTestReq {
    private Integer userId;
    private Integer testId;
    private List<QuestionAnswer> questionAnswerList;

}
