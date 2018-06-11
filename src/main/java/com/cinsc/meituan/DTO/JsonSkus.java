package com.cinsc.meituan.DTO;

import lombok.Data;

@Data
public class JsonSkus {
    private String skus;

    @Override
    public String toString() {
        return "skus:" + skus;
    }
}
