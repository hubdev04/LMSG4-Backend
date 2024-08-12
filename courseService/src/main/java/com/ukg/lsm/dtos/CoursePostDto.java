package com.ukg.lsm.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoursePostDto {
    private String title;
    private String category;
    private Long createdBy;
    private int duration;
}
