package dev.group.cybershield.quiz.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class OptionDTO {

    private Integer optionId;
    private String option;
}
