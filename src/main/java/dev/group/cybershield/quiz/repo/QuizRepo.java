package dev.group.cybershield.quiz.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import dev.group.cybershield.entity.Questions;

public interface QuizRepo extends JpaRepository<Questions,Integer> {
    Questions findbyId(Integer id);
}
