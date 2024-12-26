package dev.group.cybershield.quiz.controller;

import dev.group.cybershield.quiz.model.ReqBody;
import dev.group.cybershield.quiz.model.TestDTO;
import dev.group.cybershield.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.*;

@RestController
public class QuizContoller {

    @Autowired
    QuizService quizService;

    @PostMapping("/v1.0/getTest")
    public Map<Integer,TestDTO> getTest(@RequestBody ReqBody req) throws IOException {
        try{
            return quizService.getTestData();
        } catch (Exception e) {
            throw new IOException("unable to load test questions by getTest_API: {}", e);
        }
    }


}
