package dev.group.cybershield.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Table(name="ANSWER_TABLE")
public class Answers {
    @Id
    @Column(name="answer_master_id")
    private Integer id;

    @Column(name="answer")
    private String answers;

    @Column(name="question_id")
    private Integer questionId;

    @Column(name="is_correct")
    private String isCorrect;

    @Column(name="created_on")
    private LocalDateTime createdOn;

    @Column(name="created_by")
    private String createdBy;

    @Column(name="status")
    private String status;

}
