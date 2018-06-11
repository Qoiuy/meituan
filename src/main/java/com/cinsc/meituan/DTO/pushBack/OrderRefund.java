package com.cinsc.meituan.DTO.pushBack;

import lombok.Data;

@Data
public class OrderRefund {
    private long orderId;
    private String notifyType;//退款消息类型
    private String reason;//退款理由
}
