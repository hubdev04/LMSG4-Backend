package com.ukg.lsm.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentPostDto {
    private String commentData;
    private Long courseId;
    private Long userId;
}
