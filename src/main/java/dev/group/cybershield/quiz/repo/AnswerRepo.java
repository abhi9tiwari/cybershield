package dev.group.cybershield.quiz.repo;

import dev.group.cybershield.entity.Answers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepo extends JpaRepository<Answers,Integer> {
    List<Answers> findByQuestionId(Integer Id);

}