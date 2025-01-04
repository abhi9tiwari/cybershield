package dev.group.cybershield.quiz.service;


import dev.group.cybershield.entity.Answers;
import dev.group.cybershield.entity.Questions;
import dev.group.cybershield.quiz.model.OptionDTO;
import dev.group.cybershield.quiz.model.QuestionDTO;
import dev.group.cybershield.quiz.model.QuizReq;
import dev.group.cybershield.quiz.model.TestResponseDTO;
import dev.group.cybershield.quiz.repo.QuizAnsRepo;
import dev.group.cybershield.quiz.repo.QuizQuesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizQuesRepo quizQuesRepo;

    @Autowired
    QuizAnsRepo quizAnsRepo;


    public TestResponseDTO findUnattemptedTestByUserId(Integer userId){
        return null;
    }

    public boolean findUnattempted(Integer userId){
        return false;
    }

    List<QuestionDTO> fetchQuestionData(){
        Optional<List<Questions>> questionListObj = quizQuesRepo.getRandomQuestions();

        if(questionListObj.isPresent())
        {
            List<Questions> questionList= questionListObj.get();
            List<QuestionDTO> questionDTOList = new ArrayList<QuestionDTO>();
            for(Questions que : questionList){
                QuestionDTO queDTO =new QuestionDTO();
                queDTO.setQuestionId(que.getId());
                queDTO.setQuestion(que.getQuestion());

                List<OptionDTO> optionList= new ArrayList<OptionDTO>();
                List<Answers> ansList=quizAnsRepo.getAnswerByQuestionId(queDTO.getQuestionId());
                for(Answers ans : ansList){
                    OptionDTO option = new OptionDTO();
                    option.setOptionId(ans.getId());
                    option.setOption(ans.getAnswers());
                    optionList.add(option);
                }
                queDTO.setOptions(optionList);
                questionDTOList.add(queDTO);
            }
            return questionDTOList;
        }
        return null;
    }

    public TestResponseDTO getTestData(QuizReq reqBody) {
        List<QuestionDTO> questionList;
        TestResponseDTO testData = new TestResponseDTO();
        if(findUnattempted(reqBody.userId)){
            return findUnattemptedTestByUserId(reqBody.userId);
        }
        else{
            questionList = fetchQuestionData();
           testData.setQuestionList(questionList);

        }

        return testData;
    }
}
