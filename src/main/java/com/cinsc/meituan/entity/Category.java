package com.cinsc.meituan.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue
    private long categoryId;
    private String categoryName;
    private Integer sequence;
}
