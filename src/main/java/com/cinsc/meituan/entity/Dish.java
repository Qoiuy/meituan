package com.cinsc.meituan.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
@Data
@Entity
public class Dish {

    @Id
    private long dishId;
    private Integer boxNum;
    private BigDecimal box_price;
    private long categoryId;
    private String description;
    private String dishName;
    private Integer isSoldout;
    private Integer minOrdercount;
    private String picture;
    private BigDecimal price;
    private String unit;
    private Integer sequence;


}
