package com.cinsc.meituan.DTO;

import lombok.Data;

import java.util.List;

@Data
public class Property {
    private String propertyName;
    private List values;

    @Override
    public String toString() {
        return "{" +
                "propertyName:'" + propertyName + '\'' +
                ", values:" + values +
                '}';
    }
}
