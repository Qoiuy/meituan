package com.cinsc.meituan.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Data
@Entity
public class ShopCategory {
    @Id
    @GeneratedValue
    private long scId;
    private long shopId;
    private long categoryId;
}
