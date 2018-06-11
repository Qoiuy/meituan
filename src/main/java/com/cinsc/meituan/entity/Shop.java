package com.cinsc.meituan.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Shop {
    @Id
    private long shopId;
    private String appAuthToken;
    private long userId;
    private String platform;
    private String openTime;
    private long telephone;
    private Integer isOpen;//0是店家休息,1是店家营业
    private String name;
    private Integer isAutoConfirmOrder;//0代表不自动接单,1代表自动接单
}
