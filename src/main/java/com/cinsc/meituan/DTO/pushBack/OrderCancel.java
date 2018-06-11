package com.cinsc.meituan.DTO.pushBack;

import lombok.Data;

@Data
public class OrderCancel {
    private long orderId;
    private String reasonCode;
    private String reason;

}
