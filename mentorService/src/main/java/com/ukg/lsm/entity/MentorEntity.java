package com.ukg.lsm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

    @Entity
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public class MentorEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int mentorId;

        private String mentorName;
        private String mentorEmail;
        private String mentorPhone;



    }
