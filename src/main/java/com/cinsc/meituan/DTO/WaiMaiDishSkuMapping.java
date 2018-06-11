package com.cinsc.meituan.DTO;

import lombok.Data;

@Data
public class WaiMaiDishSkuMapping {
    private String eDishSkuCode;
    private long dishSkuId;

    @Override
    public String toString() {
        return "{" +
                "'eDishSkuCode':'" + eDishSkuCode + '\'' +
                ", 'dishSkuId':" + dishSkuId +
                '}';
    }
}
