package dev.group.cybershield.quiz.service;

import dev.group.cybershield.quiz.model.QuizReq;
import dev.group.cybershield.quiz.model.GetTestResponseDTO;
import dev.group.cybershield.quiz.model.SubmitTestReq;
import dev.group.cybershield.quiz.model.SubmitTestRes;

public interface QuizService {

    //getTest Services:
    public GetTestResponseDTO findUnattemptedTestByTestId(Integer userId) throws Exception;

    public GetTestResponseDTO fetchNewQuestionData(Integer userId) throws Exception;

    public GetTestResponseDTO getTestData(QuizReq reqBody) throws Exception;

    //submit Test Services:
    public SubmitTestRes getScore(SubmitTestReq reqBody) throws Exception;

}
