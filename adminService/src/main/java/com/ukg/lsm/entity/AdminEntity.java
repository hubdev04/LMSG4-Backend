package com.ukg.lsm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdminEntity {
    @Id
    @GeneratedValue()
    private int id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
