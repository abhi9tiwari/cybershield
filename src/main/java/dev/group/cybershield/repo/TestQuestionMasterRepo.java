package dev.group.cybershield.repo;

import dev.group.cybershield.quiz.model.TestQuestionMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@Repository
public interface TestQuestionMasterRepo extends JpaRepository<TestQuestionMap,Integer> {

    List<TestQuestionMap> findByTestId(Integer testId);

    String setUserAnswerIdByTestIdAndQuestionIdQuery = "update test_question_map set users_answer_id = :ansId, updated_on = :updatedOn  where test_id= :testId and question_id = :questionId";
    @Modifying
    @Query(value=setUserAnswerIdByTestIdAndQuestionIdQuery,nativeQuery = true)
    void setUserAnswerIdByTestIdAndQuestionId(@Param("ansId")Integer ansId, @Param("testId") Integer testId, @Param("questionId") Integer questionId, @Param("updatedOn") LocalDateTime updatedOn);
}
