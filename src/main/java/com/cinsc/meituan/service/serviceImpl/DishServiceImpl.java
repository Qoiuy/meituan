package com.cinsc.meituan.service.serviceImpl;

import com.cinsc.meituan.DTO.JsonData;
import com.cinsc.meituan.DTO.MTDish;
import com.cinsc.meituan.DTO.MTSku;
import com.cinsc.meituan.service.DishService;
import com.cinsc.meituan.util.JsonUtil;
import com.cinsc.meituan.util.MyUtil;
import com.sankuai.sjst.platform.developer.domain.RequestSysParams;
import com.sankuai.sjst.platform.developer.request.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class DishServiceImpl implements DishService {
    /**
     * 添加菜品
     *
     * @param token
     * @param ePoiId
     * @param dishes
     * @return
     */
    @Override
    public Object addDish(String token, String ePoiId, ArrayList dishes) {
        RequestSysParams params = new RequestSysParams(MyUtil.signKey, token);
        CipCaterTakeoutDishBatchUploadRequest request = new CipCaterTakeoutDishBatchUploadRequest();
        request.setRequestSysParams(params);
        request.setePoiId(ePoiId);
        request.setDishes(dishes.toString());
        try {
            return request.doRequest();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public Object updateDish(String token, String ePoiId, MTDish mtDish) {
        ArrayList dishes = new ArrayList();
        dishes.add(mtDish);
        return addDish(token,ePoiId,dishes);
    }

    /**
     * 删除菜品
     * @param token
     * @param ePoiId
     * @param eDishCode
     * @return
     */
    @Override
    public Object deleteDish(String token, String ePoiId, String eDishCode) {
        RequestSysParams params = new RequestSysParams(MyUtil.signKey,token);
        CipCaterTakeoutDishDeleteRequest request = new CipCaterTakeoutDishDeleteRequest();
        request.setRequestSysParams(params);
        request.setePoiId(ePoiId);
        request.seteDishCode(eDishCode);
        try {
            return request.doRequest();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据ERP门店Id查询门店下的菜品(包含美团方的菜品ID)
     * @param token
     * @param ePoiId
     * @return
     */
    @Override
    public Object queryBaseDishesByePoiId(String token, String ePoiId) {
        CipCaterTakeoutDishBaseQueryByEPoiIdRequest request = new CipCaterTakeoutDishBaseQueryByEPoiIdRequest();
        request.setRequestSysParams(MyUtil.getParams(token));
        request.setePoiId(ePoiId);
        try {
            return request.doRequest();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据ERP方门店Id查询门店下的菜品(不包含美团方的菜品ID)
     * @param token
     * @param ePoiId
     * @param offset
     * @param limit
     * @return
     */
    @Override
    public Object queryDishesByePoiId(String token, String ePoiId, Integer offset, Integer limit) {
        RequestSysParams params = new RequestSysParams(MyUtil.signKey,token);
        CipCaterTakeoutDishQueryByEPoiIdRequest request = new CipCaterTakeoutDishQueryByEPoiIdRequest();
        request.setRequestSysParams(params);
        request.setePoiId(ePoiId);
        request.setOffset(offset);
        request.setLimit(limit);
        try {
            return request.doRequest();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 得到不包含美团方菜品Id的dishList对象
     * @param token
     * @param ePoiId
     * @param offset
     * @param limit
     * @return
     */
    @Override
    public List<MTDish> getDishesByePoiId(String token, String ePoiId, Integer offset, Integer limit) {
        String re = (String) queryDishesByePoiId(token,ePoiId,offset,limit);
        log.info("result:{}",re);
        List dishes = JsonUtil.getDishJson(re);
        List<MTDish> dishList = new ArrayList<>();
        for (int i=0;i<dishes.size();i++){
            MTDish dish = new MTDish();

            Map map = (Map) dishes.get(i);
            log.info("result:{}",map.toString());
            dish.setBoxNum(Integer.parseInt(map.get("boxNum").toString().substring(0,map.get("boxNum").toString().indexOf("."))));
            dish.setBoxPrice(Float.parseFloat(map.get("boxPrice").toString()));
            dish.setCategoryName((String) map.get("categoryName"));
            dish.setDescription((String) map.get("description"));
            dish.setDishName((String) map.get("dishName"));
            dish.setEDishCode((String) map.get("eDishCode"));
            dish.setIsSoldOut((Integer) map.get("isSoldOut"));
            dish.setMinOrderCount((Integer) map.get("minOrderCount"));
            dish.setPicture((String) map.get("picture"));
            dish.setPrice(Float.parseFloat(map.get("price").toString()));
            dish.setSequence(map.get("sequence").toString());
            dish.setUnit((String) map.get("unit"));

            List<MTSku> skuList = new ArrayList<>();
            dish.setSkus(skuList);
            log.info("skus:{}",map.get("skus"));
            List skus = JsonUtil.getSkusJson(map.get("skus").toString());
            for (int j=0;j<skus.size();j++){
                MTSku sku = new MTSku();
                Map map1 = (Map) skus.get(j);
                sku.setAvailableTimes(map1.get("availableTimes").toString());
                sku.setLocationCode((String) map1.get("locationCode"));
                sku.setPrice(Float.parseFloat(map1.get("price").toString()));
                sku.setSkuId((String) map1.get("skuId"));
                sku.setSpec((String) map1.get("spec"));
                log.info("spec:{}",map1.get("spec"));
                sku.setStock(Integer.valueOf(map1.get("stock").toString()));
                sku.setUpc((String) map1.get("upc"));
                skuList.add(sku);
            }
            dishList.add(dish);
        }
        return dishList;
    }

    /**
     * 更新菜品的库存
     * @param token
     * @param ePoiId
     * @param dishSkuStocks
     * @return
     */
    @Override
    public Object updateStock(String token, String ePoiId, String dishSkuStocks) {
        CipCaterTakeoutDishStockUpdateRequest request = new CipCaterTakeoutDishStockUpdateRequest();
        request.setRequestSysParams(MyUtil.getParams(token));
        request.setDishSkuStocks(dishSkuStocks);
        request.setePoiId(ePoiId);
        try {
            return request.doRequest();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 对菜品的价格进行更新
     * @param token
     * @param ePoiId
     * @param dishSkuPrices
     * @return
     */
    @Override
    public Object updatePrice(String token, String ePoiId, String dishSkuPrices) {
        CipCaterTakeoutDishPriceUpdateRequest request  = new CipCaterTakeoutDishPriceUpdateRequest();
        request.setRequestSysParams(MyUtil.getParams(token));
        request.setePoiId(ePoiId);
        request.setDishSkuPrices(dishSkuPrices);
        try {
            return request.doRequest();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除菜品的Sku信息
     * @param token
     * @param eDishCode
     * @param eDishSkuCode
     * @return
     */
    @Override
    public Object deleteSku(String token, String eDishCode, String eDishSkuCode) {
        CipCaterTakeoutDishSkuDeleteRequest request = new CipCaterTakeoutDishSkuDeleteRequest();
        request.setRequestSysParams(MyUtil.getParams(token));
        request.setEDishCode(eDishCode);
        request.setEDishSkuCode(eDishSkuCode);
        try {
            return request.doRequest();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 批量创建和更新菜品属性
     * @param token
     * @param dishProperty
     * @return
     */
    @Override
    public Object updateProperty(String token, List dishProperty) {
        CipCaterTakeoutDishUpdatePropertyRequest request = new CipCaterTakeoutDishUpdatePropertyRequest();
        request.setRequestSysParams(MyUtil.getParams(token));
        request.setDishProperty(dishProperty);
        try {
            return request.doRequest();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据ePoiId查询菜品
     * @param token
     * @param ePoiId
     * @return
     */
    @Override
    public List<MTDish> queryDishesByePoiId(String token, String ePoiId) {
        String re = (String) queryBaseDishesByePoiId(token,ePoiId);
        List baseDishes = JsonUtil.getBaseDishJson(re);
        List<MTDish> mtDishes = JsonUtil.parseArray(re,MTDish.class);
        log.info("封装dishes对象:{}",re);
        List<MTDish> dishes = new ArrayList<>();
        for (int i=0;i<baseDishes.size();i++){
            Map map = (Map) baseDishes.get(i);
            MTDish dish = new MTDish();
            dish.setCategoryName((String) map.get("categoryName"));
            dish.setDishId(Long.parseLong(map.get("dishId").toString()));
            dish.setDishName((String) map.get("dishName"));
            dish.setEDishCode((String) map.get("eDishCode"));
            List<MTSku> skus = new ArrayList<>();
            dish.setSkus(skus);
            List baseSkus = JsonUtil.getBaseSkusJson(map.get("waiMaiDishSkuBases").toString());
            log.info("封装skus对象");
            for (int j=0;j<baseSkus.size();j++){
                Map map1 = (Map) baseSkus.get(j);
                MTSku sku = new MTSku();
                sku.setSkuId(map1.get("dishSkuId").toString());
                sku.setPrice(((BigDecimal)map1.get("price")).floatValue());
                sku.setSpec((String) map1.get("spec"));
                skus.add(sku);
            }
            dishes.add(dish);
        }
        return dishes;
    }

    @Override
    public MTDish getDishByDishId(String token, String ePoiId, String dishId) {
        List<MTDish> dishList = getMTDishByePoiId(token,ePoiId);
        for (MTDish dish : dishList){
            if (dish.getEDishCode().equals(dishId)){
                return dish;
            }
        }
        return null;
    }

    @Override
    public List<MTDish> getMTDishByePoiId(String token, String ePoiId) {
        String re = (String) queryDishesByePoiId(token,ePoiId,1,999);
        JsonData data = JsonUtil.parseObject(re,JsonData.class);
        List<MTDish> mtDishList = JsonUtil.parseArray(data.getData(),MTDish.class);
        return mtDishList;
    }


}
