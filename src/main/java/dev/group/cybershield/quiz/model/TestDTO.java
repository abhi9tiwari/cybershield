package dev.group.cybershield.quiz.model;

import lombok.Data;
import java.util.*;

@Data
public class TestDTO {
    public QuestionDTO question;
    public List<AnswerDTO> answer;
}
