package dev.group.cybershield.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="answer_master")
public class Answers {
    @Id
    @Column(name="answer_id")
    private Integer id;

    @Column(name="answer")
    private String answers;

    @Column(name="question_id")
    private Integer questionId;

    @Column(name="is_correct")
    private String isCorrect;

    @Column(name="status")
    private String status;

    @Column(name="created_on")
    private LocalDateTime createdOn;

    @Column(name="updated_on")
    private LocalDateTime updatedOn;
}
