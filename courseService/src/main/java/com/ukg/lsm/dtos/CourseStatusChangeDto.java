package com.ukg.lsm.dtos;

import com.ukg.lsm.configuration.CourseApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CourseStatusChangeDto {
    Long adminId;
    Long courseId;
    CourseApprovalStatus status;
}
