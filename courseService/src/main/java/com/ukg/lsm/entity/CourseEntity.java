package com.ukg.lsm.entity;

import com.ukg.lsm.configuration.CourseApprovalStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data

public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String category;
    private Long createdBy;
    private Long duration;
    @Enumerated(EnumType.STRING)
    private CourseApprovalStatus approvalStatus;
    private LocalDate createdDate;
    private LocalDate lastModifiedDate;
    private Long lastModifiedBy;
    private Boolean isActive;
    private Boolean isDeleted;
}
