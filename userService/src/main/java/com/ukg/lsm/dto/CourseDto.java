package com.ukg.lsm.dto;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data

public class CourseDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String category;
    private Long createdBy;
    private Long duration;

    private String approvalStatus;
    private LocalDate createdDate;
    private LocalDate lastModifiedDate;
    private Long lastModifiedBy;
    private Boolean isActive;
    private Boolean isDeleted;
}
