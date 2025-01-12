package dev.group.cybershield.quiz.service;

import dev.group.cybershield.entity.Answers;
import dev.group.cybershield.entity.Questions;
import dev.group.cybershield.entity.TestMaster;
import dev.group.cybershield.quiz.model.*;
import dev.group.cybershield.repo.QuizAnsRepo;
import dev.group.cybershield.repo.QuizQuesRepo;
import dev.group.cybershield.repo.TestMasterRepo;
import dev.group.cybershield.repo.TestQuestionMasterRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class QuizServiceImp implements  QuizService {

    @Autowired
    private QuizQuesRepo quizQuesRepo;

    @Autowired
    private QuizAnsRepo quizAnsRepo;

    @Autowired
    private TestMasterRepo testMasterRepo;

    @Autowired
    private TestQuestionMasterRepo testQuestionMasterRepo;

//getTest API:
    @Override
    public TestResponseDTO findUnattemptedTestByTestId(Integer testId)
    {
        try{
            log.info("findUnattemptedTestByTestId_API testId: {}", testId);
            TestResponseDTO testData= new TestResponseDTO();
            List<QuestionDTO> questionDTOList =new ArrayList<QuestionDTO>();
            testData.setTestId(testId);
            List<TestQuestionMap> testQuestionMapList = testQuestionMasterRepo.findByTestId(testId);
            for(TestQuestionMap testQueMapData: testQuestionMapList){
                QuestionDTO question =new QuestionDTO();
                question.setQuestionId(testQueMapData.getQuestionId());
                Optional<Questions> questionFromDB =quizQuesRepo.findById(testQueMapData.getQuestionId());
                if(questionFromDB.isPresent()){
                    question.setQuestion(questionFromDB.get().getQuestion());
                }
                List<OptionDTO> options = new ArrayList<OptionDTO>();

                OptionDTO option1 = new OptionDTO();
                option1.setOptionId(testQueMapData.getGivenOption1());
                option1.setOption(quizAnsRepo.findById(testQueMapData.getGivenOption1()).get().getAnswers());
                options.add(option1);

                OptionDTO option2 = new OptionDTO();
                option2.setOptionId(testQueMapData.getGivenOption2());
                option2.setOption(quizAnsRepo.findById(testQueMapData.getGivenOption2()).get().getAnswers());
                options.add(option2);

                OptionDTO option3 = new OptionDTO();
                option3.setOptionId(testQueMapData.getGivenOption3());
                option3.setOption(quizAnsRepo.findById(testQueMapData.getGivenOption3()).get().getAnswers());
                options.add(option3);

                OptionDTO option4 = new OptionDTO();
                option4.setOptionId(testQueMapData.getGivenOption4());
                option4.setOption(quizAnsRepo.findById(testQueMapData.getGivenOption4()).get().getAnswers());
                options.add(option4);

                question.setOptions(options);
                questionDTOList.add(question);
            }
            testData.setQuestionList(questionDTOList);
            return testData;
        } catch (Exception e) {
            log.error("findUnattemptedTestByTestId_API_error: {}",e.getMessage());
            throw new RuntimeException(e);
        }

    }


    @Override
    public TestResponseDTO fetchNewQuestionData(Integer userId){
        try {
            log.info("fetchNewQuestionData_API userId:{}",userId);
            TestResponseDTO testData = new TestResponseDTO();
            Optional<List<Questions>> questionListObj = quizQuesRepo.getRandomQuestions();
            Integer testId = null;

            if (questionListObj.isPresent()) {
                TestMaster testMasterData = new TestMaster();
                testMasterData.setUserId(userId);
                testMasterData.setTestStatus("init");
                testMasterData.setScore(0);
                testMasterData.setCreatedOn(LocalDateTime.now());
                testMasterData.setUpdatedOn(LocalDateTime.now());
                testMasterData.setStatus("A");
                testId = testMasterRepo.save(testMasterData).getTestId();
                testData.setTestId(testId);
                log.info("fetchNewQuestionData_API userId:{} and testId:{} ",userId,testId);

                List<Questions> questionList = questionListObj.get();
                List<QuestionDTO> questionDTOList = new ArrayList<QuestionDTO>();
                for (Questions que : questionList) {
                    QuestionDTO queDTO = new QuestionDTO();
                    queDTO.setQuestionId(que.getId());
                    queDTO.setQuestion(que.getQuestion());

                    TestQuestionMap testQuestionMapData = new TestQuestionMap();
                    testQuestionMapData.setTestId(testId);
                    testQuestionMapData.setQuestionId(que.getId());
                    testQuestionMapData.setUsersAnswerId(1);
                    testQuestionMapData.setStatus("A");

                    List<OptionDTO> optionList = new ArrayList<OptionDTO>();
                    List<Answers> ansList = quizAnsRepo.getAnswerByQuestionId(queDTO.getQuestionId());

                    int count = 1;
                    for (Answers ans : ansList) {
                        OptionDTO option = new OptionDTO();
                        option.setOptionId(ans.getId());
                        option.setOption(ans.getAnswers());
                        optionList.add(option);

                        switch (count) {
                            case 1:
                                testQuestionMapData.setGivenOption1(ans.getId());
                                break;
                            case 2:
                                testQuestionMapData.setGivenOption2(ans.getId());
                                break;
                            case 3:
                                testQuestionMapData.setGivenOption3(ans.getId());
                                break;
                            case 4:
                                testQuestionMapData.setGivenOption4(ans.getId());
                                break;
                        }
                        count++;
                    }

                    queDTO.setOptions(optionList);
                    questionDTOList.add(queDTO);

                    testQuestionMapData.setUpdatedOn(LocalDateTime.now());
                    testQuestionMapData.setCreatedOn(LocalDateTime.now());
                    testQuestionMasterRepo.save(testQuestionMapData);

                }
                testData.setQuestionList(questionDTOList);
            }
            return testData;
        } catch (Exception e) {
            log.error("fetchNewQuestionData_API_error: {}",e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public TestResponseDTO getTestData(QuizReq reqBody) {
        try{
            log.info("getTestData_API userId:{}",reqBody.userId);
            TestResponseDTO testData = new TestResponseDTO();
            Integer unattemptedTestId=testMasterRepo.findUnattemptedTest(reqBody.userId);
            if(unattemptedTestId!=null){
                testData= findUnattemptedTestByTestId(unattemptedTestId);
            }
            else{
                testData = fetchNewQuestionData(reqBody.userId);
            }
            return testData;
        } catch (Exception e) {
            log.error("getTestData_API_error: {}",e.getMessage());
            throw new RuntimeException(e);
        }

    }

    // viewTest API
}
