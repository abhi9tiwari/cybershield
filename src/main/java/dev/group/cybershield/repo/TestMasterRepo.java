package dev.group.cybershield.repo;

import dev.group.cybershield.entity.TestMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface TestMasterRepo extends JpaRepository<TestMaster,Integer> {

    String findUnattemptedTestQuery ="select test_id from test_master where user_id= :userId and test_status='init' order by test_id desc limit 1 ";
    @Query(value=findUnattemptedTestQuery, nativeQuery = true)
    Integer findUnattemptedTest(@Param("userId") Integer userId);

    String saveScoreAndCompleteTestQuery="update test_master set score=:score, updated_on= :updatedOn where test_id = :testId ";
    @Modifying
    @Query(value=saveScoreAndCompleteTestQuery,nativeQuery = true)
    void saveScoreCompleteTest(@Param("score") int score, @Param("updatedOn") LocalDateTime updatedOn, @Param("testId") Integer testId);
}
