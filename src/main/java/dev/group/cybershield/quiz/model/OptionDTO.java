package dev.group.cybershield.quiz.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class AnswerDTO {

    @JsonAlias("answer_id")
    private Integer answerId;

    private Integer answer;
}
