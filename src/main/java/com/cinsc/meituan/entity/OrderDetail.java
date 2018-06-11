package com.cinsc.meituan.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
public class OrderDetail {
    @Id
    private long detailId;
    private String orderId;
    private long dishId;
    private Integer boxNum;
    private BigDecimal boxPrice;
    private String dishName;
    private BigDecimal price;
    private long skuId;
    private Integer quantity;
    private String unit;
    private Date createTime;
    private Date updateTime;

}
