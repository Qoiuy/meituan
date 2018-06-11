package com.cinsc.meituan.DTO.pushBack;

import lombok.Data;

@Data
public class PushOrderData {
    private Integer developerId;
    private String ePoiId;
    private String sign;
    private String order;
    private PushOrder pushOrder;


}
