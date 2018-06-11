package com.cinsc.meituan.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MTSku {
    private String availableTimes;
    private String locationCode;
    private float price;//*
    private String skuId;//*ERP方sku ID
    private String spec;//*必须 菜品的规格名称
    private Integer stock;//*
    private String upc;

    @Override
    public String toString() {
        return "{" +
                "skuId:'" + skuId + '\'' +
                ", spec:'" + spec + '\'' +
                ", upc:'" + upc + '\'' +
                ", stock:" + stock +
                ", price:" + price +
                ", availableTimes:" + availableTimes +
                ", locationCode:'" + locationCode + '\'' +
                '}';
    }
}
