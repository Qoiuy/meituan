package com.cinsc.meituan.DTO.pushBack;

import lombok.Data;

@Data
public class ShippingStatus {
    private long orderId;
    //0-配送单发往配送;10-配送单已确认;20-骑手已取餐;40-骑手已送达;100-配送单已取消
    private Integer shippingStatus;
    private long time;
    private String dispatcherName;
    private String dispatcherMobile;
}
