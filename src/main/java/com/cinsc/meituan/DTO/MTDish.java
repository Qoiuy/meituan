package com.cinsc.meituan.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MTDish {
    private Integer boxNum;//创建时必须
    private float boxPrice;//创建时必须
    private String categoryName;//*
    private String description;
    private String dishName;//*
    private long dishId;//queryBaseDishesByePoiIds时返回的美团方菜品Id使用.
    private String EDishCode;//*
    private String ePoiId;//*
    private Integer isSoldOut;//创建时必须
    private Integer minOrderCount;//购买最小数量,创建是必须
    private String picture;
    private String sequence;
    private float price;//*
    private String unit;//创建时必须
    private List<MTSku> skus;

    @Override
    public String toString() {
        return "{" +
                "boxNum:" + boxNum +
                ", boxPrice:" + boxPrice +
                ", categoryName:'" + categoryName +
                "', description:'" + description +
                "', dishName:'" + dishName +
                "', EDishCode:'" + EDishCode +
                "', ePoiId:'" + ePoiId +
                "', isSoldOut:" + isSoldOut +
                ", minOrderCount:" + minOrderCount +
                ", picture:'" + picture +
                "', sequence:'" + sequence +
                "', price:" + price +
                ", unit:'" + unit +
                "', skus:" + skus +
                "}";
    }
}
