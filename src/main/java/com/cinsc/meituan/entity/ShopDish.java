package com.cinsc.meituan.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class ShopDish {
    @Id
    @GeneratedValue
    private long sdId;
    private long shopId;
    private long dishId;
}
