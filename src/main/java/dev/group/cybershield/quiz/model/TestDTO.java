package dev.group.cybershield.quiz.model;

import dev.group.cybershield.entity.Answers;
import dev.group.cybershield.entity.Questions;
import lombok.Data;
import java.util.*;

@Data
public class TestDTO {
    public Questions question;
    public List<Answers> answersList;
}
