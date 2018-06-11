package com.cinsc.meituan.DTO;

import lombok.Data;

@Data
public class BaseDish {
    private String eDishCode;//ERP方ID
    private long dishId;//美团方菜品ID
    private String dishName;
    private String categoryName;
    private String waiMaiDishSkuBases;

}
