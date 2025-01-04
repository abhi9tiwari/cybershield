package dev.group.cybershield.quiz.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class QuestionDTO {

    @JsonAlias(value="question_id")
    private Integer questionId;

    private String question;

    private List<OptionDTO> options;
}
