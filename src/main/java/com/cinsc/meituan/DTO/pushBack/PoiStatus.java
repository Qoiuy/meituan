package com.cinsc.meituan.DTO.pushBack;

import lombok.Data;

@Data
public class PoiStatus {
    private String ePoiId;
    private Integer poiStatus;
    private String operateUser;
    private String reason;
    private long operateTime;
}
