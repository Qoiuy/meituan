package com.cinsc.meituan.service;

import com.cinsc.meituan.DTO.MTDish;

import java.util.ArrayList;
import java.util.List;

public interface DishService {
    Object addDish(String token, String ePoiId, ArrayList dishes);//添加或更新菜品信息
    Object updateDish(String token, String ePoiId, MTDish mtDish);
    Object deleteDish(String token,String ePoiId,String eDishCode);
    Object queryBaseDishesByePoiId(String token,String ePoiId);
    Object queryDishesByePoiId(String token, String ePoiId, Integer offset, Integer limit);//可以限制数目,但无法选择查询
    List<MTDish> getDishesByePoiId(String token, String ePoiId, Integer offset, Integer limit);
    Object updateStock(String token,String ePoiId,String dishSkuStocks);
    Object updatePrice(String token, String ePoiId, String dishSkuPrices);
    Object deleteSku(String token,String eDishCode,String eDishSkuCode);
    Object updateProperty(String token,List dishProperty);
    List<MTDish> queryDishesByePoiId(String token, String ePoiId);
    MTDish getDishByDishId(String token, String ePoiId, String dishId);
    List<MTDish> getMTDishByePoiId(String token,String ePoiId);
}
