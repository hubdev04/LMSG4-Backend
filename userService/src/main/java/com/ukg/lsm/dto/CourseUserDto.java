package com.ukg.lsm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CourseUserDto {
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
