package dev.group.cybershield.quiz.service;

import dev.group.cybershield.entity.Questions;
import dev.group.cybershield.quiz.model.TestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuizService {
    @Autowired
    Questions queEnt;

    public List<TestDTO> getTestData(){
        Map<Integer,TestDTO> questions = new HashMap<Integer,TestDTO>();
        Random random = new Random();
        int Rnum;
        for(int i=0;i<10;i++){
            Rnum=random.nextInt(20);
            if(!questions.containsKey(Rnum)){
                queEnt.getDescription();

            }
        }
        List<TestDTO> testData = new ArrayList<TestDTO>();
        return testData;
    }
}
