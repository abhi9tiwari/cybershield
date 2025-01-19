package dev.group.cybershield.quiz;

import dev.group.cybershield.common.constants.Constants;
import dev.group.cybershield.common.exception.BadRequestException;
import dev.group.cybershield.common.global.ResponseDTO;
import dev.group.cybershield.common.utils.ResponseUtil;
import dev.group.cybershield.entity.Questions;
import dev.group.cybershield.repository.QuestionRepo;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@Transactional
@RequestMapping("/test")
public class TestController {

    @Autowired
    private QuestionRepo questionRepo;

    @PostMapping("/v1.0/getQuestion")
    public ResponseEntity<ResponseDTO> getQuestions(@RequestBody Questions request) {
        String endPoint = "getQuestions";
        Timestamp landingTime = Timestamp.valueOf(LocalDateTime.now());

        if(request.getId() == null) {
            throw new BadRequestException("Id is mandatory");
        }

        Optional<Questions> questionData = questionRepo.findById(request.getId());
        log.info("fetched data from database " + questionData.get());
        return ResponseUtil.sendResponse(questionData.get(), landingTime, HttpStatus.OK, 200, Constants.SUCCESS, endPoint);
    }

    @GetMapping("/v1.0/getAllQuestions")
    public ResponseEntity<ResponseDTO>  getAllQuestions(){
        String endPoint = "getAllQuestions";
        Timestamp landingTime = Timestamp.valueOf(LocalDateTime.now());
        List<Questions> allQuestions = questionRepo.findAll();
        return ResponseUtil.sendResponse(allQuestions , landingTime, HttpStatus.OK, 200, Constants.SUCCESS , endPoint);
    }
    
}
