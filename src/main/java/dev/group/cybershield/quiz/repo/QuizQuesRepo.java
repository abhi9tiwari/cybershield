package dev.group.cybershield.quiz.repo;

import dev.group.cybershield.entity.Questions;
import dev.group.cybershield.quiz.model.QuestionDTO;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface QuizQuesRepo extends JpaRepository<Questions,Integer> {

    String randomQuestionQuery =" select * from Question_Master order by RANDOM() limit 10 ";
    @Query(value=randomQuestionQuery,nativeQuery = true)
    Optional<List<Questions>> getRandomQuestions();
}
