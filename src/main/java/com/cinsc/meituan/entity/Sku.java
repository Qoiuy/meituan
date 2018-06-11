package com.cinsc.meituan.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
public class Sku {
    @Id
    private long skuId;
    private long dishId;
    private BigDecimal price;
    private Integer stock;
    private String spec;
}
