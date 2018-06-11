package com.cinsc.meituan.DTO;

import lombok.Data;

@Data
public class WaiMaiDishSkuBases {
    private long dishSkuId;
    private String dishSkuName;
    private String eDishSkuCode;
    private String spec;
    private String description;
    private float price;

    @Override
    public String toString() {
        return "{" +
                "dishSkuId:" + dishSkuId +
                ", dishSkuName:'" + dishSkuName + '\'' +
                ", eDishSkuCode:'" + eDishSkuCode + '\'' +
                ", spec:'" + spec + '\'' +
                ", description:'" + description + '\'' +
                ", price:" + price +
                '}';
    }
}
