package com.cinsc.meituan.DTO;

import lombok.Data;

import java.util.List;

@Data
public class DishMapping {
    private long dishId;
    private String eDishCode;
    private List waiMaiDishSkuMappings;

    @Override
    public String toString() {
        return "{" +
                "'dishId':" + dishId +
                ", 'eDishCode':'" + eDishCode + '\'' +
                ", 'waiMaiDishSkuMappings':" + waiMaiDishSkuMappings.toString() +
                '}';
    }
}
