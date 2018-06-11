package com.cinsc.meituan.DTO;

import lombok.Data;

import java.util.List;
@Data
public class DishProperty {
    private String eDishCode;
    private List properties;

    @Override
    public String toString() {
        return "{" +
                "eDishCode:'" + eDishCode + '\'' +
                ", properties:" + properties +
                '}';
    }
}
