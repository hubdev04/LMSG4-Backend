package com.ukg.lsm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class CourseUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long courseId;
    private Long userId;
    private int completion;
    private LocalDate createdDate;
    private LocalDate lastModifiedDate;
    private Long createdBy;
    private Long lastModifiedBy;
    private Boolean isActive;
    private Boolean isDeleted;
}
