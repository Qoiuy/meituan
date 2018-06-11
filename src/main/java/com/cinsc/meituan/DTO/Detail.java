package com.cinsc.meituan.DTO;

import lombok.Data;

@Data
public class Detail {
    private String app_food_code;//ERP方菜品的Id,等于eDishCode
    private Integer box_num;
    private float box_price;
    private String food_name;
    private float price;
    private String sku_id;//ERP方菜品的sku
    private Integer quantity;
    private String unit;//单位
    private float food_discount;//菜品折扣
    private String spec;//菜品规格
    private String food_property;
    private float foodShareFeeChargeByPoi;//菜品分成,订单中给美团的分成金额
    private Integer cart_id;//商品所在分类

    @Override
    public String toString() {
        return "{" +
                "app_food_code:'" + app_food_code + '\'' +
                ", box_num:" + box_num +
                ", box_price:" + box_price +
                ", food_name:'" + food_name + '\'' +
                ", price:" + price +
                ", sku_id:'" + sku_id + '\'' +
                ", quantity:" + quantity +
                ", unit:'" + unit + '\'' +
                ", food_discount:" + food_discount +
                ", spec:'" + spec +'\'' +
                ", food_property:'" + food_property + '\'' +
                ", foodShareFeeChargeByPoi:" + foodShareFeeChargeByPoi +
                ", cart_id:" + cart_id +
                '}';
    }
}
