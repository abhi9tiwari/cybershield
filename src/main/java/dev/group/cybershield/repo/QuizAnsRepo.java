package dev.group.cybershield.repo;

import dev.group.cybershield.entity.Answers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizAnsRepo extends JpaRepository<Answers,Integer> {

    String answerByQuestionId ="select * from answer_master where question_id=:question_id";
    @Query(value=answerByQuestionId,nativeQuery = true)
    List<Answers> getAnswerByQuestionId(@Param("question_id") Integer id);

    String findCorrectAnswer="select answer_id from answer_master where question_id = :questionId and is_correct = 'Y' ";
    @Query(value= findCorrectAnswer,nativeQuery = true)
    Integer findCorrectAnswer(@Param("questionId") Integer questionId);
}
