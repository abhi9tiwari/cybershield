package dev.group.cybershield.quiz.model;

import lombok.Data;

@Data
public class QuestionAnswer {
    private Integer questionId;
    private Integer selectedOptionId;
}
