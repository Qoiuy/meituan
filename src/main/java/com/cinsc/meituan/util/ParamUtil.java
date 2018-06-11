package com.cinsc.meituan.util;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
@Slf4j
public class ParamUtil {

    //得到dishes中的指定的dish的某个字段的String值
    public static String getDishParam(List dishes,String eDishCode,String findParam){

        Map map;
        for (int i=0;i<dishes.size();i++){
            if(((Map)dishes.get(i)).get("eDishCode").equals(eDishCode)){
                map = (Map) dishes.get(i);
                return map.get(findParam).toString();
            }
        }
        return null;

    }
    //得到skus中的指定sku的某个字段的String值
    public static String getSkuParam(List dishes, String eDishCode, String skuId, String findParam){
        List skus = JsonUtil.getSkusJsonByEDishCode(dishes,eDishCode);
        log.info("list中的skus:{}",skus.toArray());
        Map map;
        for (int i=0;i<skus.size();i++){
            if(((Map)skus.get(i)).get("skuId").equals(skuId)){
                map = (Map) skus.get(i);
                return (String) map.get(findParam);
            }
        }
        return null;
    }
}

