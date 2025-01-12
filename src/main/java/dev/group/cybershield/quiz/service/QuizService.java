package dev.group.cybershield.quiz.service;

import dev.group.cybershield.entity.Answers;
import dev.group.cybershield.entity.Questions;
import dev.group.cybershield.quiz.model.OptionDTO;
import dev.group.cybershield.quiz.model.QuestionDTO;
import dev.group.cybershield.quiz.model.QuizReq;
import dev.group.cybershield.quiz.model.TestResponseDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface QuizService {

    public TestResponseDTO findUnattemptedTestByTestId(Integer userId);

    public TestResponseDTO fetchNewQuestionData(Integer userId);

    public TestResponseDTO getTestData(QuizReq reqBody);

}
