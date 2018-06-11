package com.cinsc.meituan.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
public class OrderMaster {
    @Id
    private String orderId;
    private String customerName;
    private String sendAddress;
    private long customerTelephone;
    private BigDecimal deliverFee;
    private long shopId;
    private String caution;//备注
    private BigDecimal totalPrice;
    private Integer orderStatus;//默认0为新下单
    private Integer payStatus;//默认0为未支付
    private Date createTime;
    private Date updateTime;
}
