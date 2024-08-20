package com.ukg.lsm.entity;

import com.ukg.lsm.configuration.CourseApprovalStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String title;
    @Column(nullable = false)
    private String category;
    private String description;
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
