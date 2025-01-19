package dev.group.cybershield.quiz.controller;

import dev.group.cybershield.common.global.ResponseDTO;
import dev.group.cybershield.common.utils.ResponseUtil;
import dev.group.cybershield.quiz.model.QuizReq;
import dev.group.cybershield.quiz.model.GetTestResponseDTO;
import dev.group.cybershield.quiz.model.SubmitTestReq;
import dev.group.cybershield.quiz.model.SubmitTestRes;
import dev.group.cybershield.quiz.service.QuizServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    QuizServiceImp quizService;

    @PostMapping("/v1.0/getTest")
    public ResponseEntity<ResponseDTO> getTest(@RequestBody QuizReq reqBody) throws Exception {
        try{
            String endPoint = "getTest";
            Timestamp landingTime = Timestamp.valueOf(LocalDateTime.now());

            if(reqBody.getUserId() == null ) {
                throw new Exception("UserId are mandatory");
            }

            if(reqBody.getTestId() == null ) {
                throw new Exception("TestId are mandatory");
            }

            GetTestResponseDTO response = quizService.getTestData(reqBody);
            log.info("gtTest_API " + response);
            return ResponseUtil.sendResponse(response, landingTime, HttpStatus.OK, 200, "Successfully" , endPoint);
        } catch (Exception e) {
            log.error("unable to load test questions by getTest_API: {}", e.getMessage());
            throw e;
        }
    }

    @PostMapping("/v1.0/submitTest")
    public ResponseEntity<ResponseDTO> submitTest(@RequestBody SubmitTestReq reqBody) throws Exception {
        try{
            String endPoint = "submitTest";
            Timestamp landingTime = Timestamp.valueOf(LocalDateTime.now());
            SubmitTestRes response = quizService.getScore(reqBody);
            log.info("submitTest_API " + response);
            return ResponseUtil.sendResponse(response, landingTime, HttpStatus.OK, 200, "Successfully" , endPoint);
        } catch (Exception e) {
            log.error("submitTest_API_error: {}", e.getMessage());
            throw e;
        }
    }

    @PostMapping("/v1.0/viewTest")
    public ResponseEntity<ResponseDTO> viewTest(@RequestBody QuizReq reqBody) throws Exception {
        try{
            String endPoint = "viewTest";
            Timestamp landingTime = Timestamp.valueOf(LocalDateTime.now());
            GetTestResponseDTO response = quizService.getTestData(reqBody);
            log.info("fetched data from database " + response);
            return ResponseUtil.sendResponse(response, landingTime, HttpStatus.OK, 200, "Successfully" , endPoint);
        } catch (Exception e) {
            log.error("unable to load test questions by getTest_API: {}", e.getMessage());
            throw e;
        }
    }
}
