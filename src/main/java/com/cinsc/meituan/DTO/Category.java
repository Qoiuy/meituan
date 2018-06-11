package com.cinsc.meituan.DTO;

import lombok.Data;

@Data
public class Category {
    private String ePoiId;
    private String name;
    private Integer sequence;

    @Override
    public String toString() {
        return "ePoiId='" + ePoiId + '\'' +
                ", name='" + name + '\'' +
                ", sequence=" + sequence;
    }

}
