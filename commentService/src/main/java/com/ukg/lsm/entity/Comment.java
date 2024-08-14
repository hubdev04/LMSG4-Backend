package com.ukg.lsm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Comment {
    @Id
    @GeneratedValue()
    private Long commentId;
    private String commentData;
    private Long courseId;
    private Long userId;

    private Boolean isActive;
    private Boolean isDeleted;





}
