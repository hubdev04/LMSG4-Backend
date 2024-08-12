package com.ukg.lsm.dto;

import com.ukg.lsm.entity.MentorEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MentorCoursesResponse {
    private MentorEntity mentor;
    private Courses courses;
}
