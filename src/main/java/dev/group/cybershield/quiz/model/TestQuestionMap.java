package dev.group.cybershield.quiz.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="test_question_map")
public class TestQuestionMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="test_question_map_id")
    private Integer testQuestionMapId;

    @Column(name="test_id",nullable = false)
    private Integer testId;

    @Column(name="question_id",nullable = false)
    private Integer questionId;

    @Column(name="users_answer_id")
    private Integer usersAnswerId;

    @Column(name="given_option1",nullable = false)
    private Integer givenOption1;

    @Column(name="given_option2",nullable = false)
    private Integer givenOption2;

    @Column(name="given_option3",nullable = false)
    private Integer givenOption3;

    @Column(name="given_option4",nullable = false)
    private Integer givenOption4;

    @Column(name="created_on")
    private LocalDateTime createdOn;

    @Column(name="updated_on")
    private LocalDateTime updatedOn;

    private String status;
}
