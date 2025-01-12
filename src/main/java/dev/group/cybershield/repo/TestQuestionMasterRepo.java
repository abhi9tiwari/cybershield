package dev.group.cybershield.repo;

import dev.group.cybershield.quiz.model.TestQuestionMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestQuestionMasterRepo extends JpaRepository<TestQuestionMap,Integer> {

    List<TestQuestionMap> findByTestId(Integer testId);
}
