package dev.group.cybershield.quiz.service;

import dev.group.cybershield.entity.Questions;
import dev.group.cybershield.quiz.model.TestDTO;
import dev.group.cybershield.quiz.repo.AnswerRepo;
import dev.group.cybershield.quiz.repo.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuizService {
    @Autowired
    QuizRepo quizRepo;

    @Autowired
    AnswerRepo ansRepo;

    public List<TestDTO> getTestData(){
        Map<Integer,Questions> questionsLst = new HashMap<Integer,Questions>();
        List<TestDTO> testDataLst = new ArrayList<TestDTO>();
        Random random = new Random();
        int Rnum;
        for(int i=0;i<10;i++) {
            Rnum = random.nextInt(20);
            if (!questionsLst.containsKey(Rnum)) {
                TestDTO tempTestData =new TestDTO();
                questionsLst.put(Rnum, quizRepo.findbyId(Rnum));
                tempTestData.setQuestion(questionsLst.get(Rnum));
                tempTestData.setAnswersList(ansRepo.findByQuestionId(Rnum));
                testDataLst.add(tempTestData);
            }
        }
        return testDataLst;
    }
}
