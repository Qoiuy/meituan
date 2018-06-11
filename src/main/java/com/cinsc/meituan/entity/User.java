package com.cinsc.meituan.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue
    private long userId;
    @Column(unique = true)
    private String userName;
    private String userPassword;
    private String telephone;
    private String emailAddress;
    private String role;

}
