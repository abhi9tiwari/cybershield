package dev.group.cybershield.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Table(name ="test_master")
@Entity
public class TestMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="test_id")
    private Integer testId;

    @Column(name = "user_id",nullable = false)
    private Integer userId;

    @Column(nullable = false)
    private Integer score;

    private String category;

    private String level;

    @Column(name="test_status", nullable = false)
    private String testStatus;

    @Column(name="created_on")
    private LocalDateTime createdOn;

    @Column(name="updated_on")
    private LocalDateTime updatedOn;

    private String status;

}
