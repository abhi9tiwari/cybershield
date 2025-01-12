package dev.group.cybershield.quiz.model;


import lombok.Data;

import java.util.List;

@Data
public class GetTestResponseDTO {
    private Integer testId;
    private List<QuestionDTO> questionList;
}
