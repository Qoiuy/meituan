package com.cinsc.meituan.DTO;

import lombok.Data;

import java.util.List;

@Data
public class DishSku {
    private String eDishCode;
    private List skus;

    @Override
    public String toString() {
        return "{" +
                "eDishCode:'" + eDishCode + '\'' +
                ", skus:" + skus +
                '}';
    }
}
