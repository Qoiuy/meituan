package com.cinsc.meituan.DTO;

import lombok.Data;

@Data
public class FoodInfo {
    private String app_food_code;
    private Integer box_num;
    private double box_price;
    private Integer count;
    private String food_name;
    private double food_price;
    private double origin_food_price;
    private double refund_price;
    private String sku_id;
}
