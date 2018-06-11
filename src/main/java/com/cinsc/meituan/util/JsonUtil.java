package com.cinsc.meituan.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.cinsc.meituan.DTO.*;
import com.cinsc.meituan.DTO.pushBack.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 工具类
 *
 * @author sowhat
 *
 */
@Slf4j
public class JsonUtil {
    /**
     * 解析JsonObject数据
     *
     * @param jsonString
     *            Json格式字符串
     * @param cls
     *            封装类
     *
     */
    public static <T> T parseObject(String jsonString, Class<T> cls) {
        T t = null;
        try {
            t = JSON.parseObject(jsonString, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 解析JsonArray数据
     *
     * @param jsonString
     * @param cls
     * @return
     */
    public static <T> List<T> parseArray(String jsonString, Class<T> cls) {
        List<T> list = new ArrayList<T>();
        try {
            list = JSON.parseArray(jsonString, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 解析JsonArray数据，返回Map类型的List
     *
     * @param jsonString
     * @return
     */
    public static List<Map<String, Object>> parseObjectListKeyMaps(
            String jsonString) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            list = JSON.parseObject(jsonString,
                    new TypeReference<List<Map<String, Object>>>() {
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 解析菜品类传回的json
     * 解析成对应的Map集合
     * @param catJson
     * @return
     */
    public static List getCatJson(String catJson){
        JsonData catData = parseObject(catJson, JsonData.class);
        List categories = parseObjectListKeyMaps(catData.getData());
        return categories;
    }

    /**
     * 解析查询菜品返回的json
     * 解析成对应的Map集合
     * @param dishJson
     * @return
     */
    public static List getDishJson(String dishJson){
        log.info("dishJson:{}",dishJson);
        JsonData data = parseObject(dishJson,JsonData.class);
        List dishes = parseObjectListKeyMaps(data.getData());
        return dishes;
    }

    /**
     * 解析菜品下的skus
     * @param skusJson
     * @return
     */
    public static List getSkusJson(String skusJson){
        log.info("skusJson:{}",skusJson);
        JsonSkus skus = parseObject("{'skus':"+skusJson+"}",JsonSkus.class);
        List skuList= parseObjectListKeyMaps(skus.getSkus());
        return skuList;
    }

    /**
     * 根据菜品列表和菜品的Id得到skus
     * @param dishes
     * @param eDishCode
     * @return
     */
    public static List getSkusJsonByEDishCode(List dishes,String eDishCode){
        String skus = ParamUtil.getDishParam(dishes,eDishCode,"skus");
        log.info("找到dish中的skus:{}",skus);
        return JsonUtil.getSkusJson(skus);
    }


    /**
     * 解析Base查询方式查出的菜品json
     * @param baseDishJson
     * @return
     */
    public static List getBaseDishJson(String baseDishJson){
        JsonData data = parseObject(baseDishJson,JsonData.class);
        List baseDishes = parseObjectListKeyMaps(data.getData());
        return baseDishes;
    }

    public static List getBaseSkusJson(String baseSkusJson){
        log.info("baseSkuJson:{}",baseSkusJson);
        JsonSkus skus = parseObject("{'skus':"+baseSkusJson+"}",JsonSkus.class);
        log.info("skus:{}",skus.toString());
        List baseSkuList = parseObjectListKeyMaps(skus.getSkus());
        return baseSkuList;
    }
    /**
     * 解析查询的门店信息
     * @param shopJson
     * @return
     */
    public static List getShopInfoJson(String shopJson){
        JsonData data = parseObject(shopJson,JsonData.class);
        List shopInfoList = parseObjectListKeyMaps(data.getData());
        return shopInfoList;
    }

    /**
     * 凭订单号进行查询返回的Order进行Json解析
     * @param orderJson
     * @return
     */
    public static Order getOrderJson(String orderJson){
        log.info("orderJson:{}",orderJson);
        JsonData data = parseObject(orderJson,JsonData.class);
        return parseObject(data.getData(),Order.class);
    }

    /**
     * 解析推送订单的信息
     * @param orderJson
     * @return
     */
    public static PushOrder getPushOrder(String orderJson){
        PushOrder pushOrder = parseObject(orderJson,PushOrder.class);
        List<Detail> details  = parseArray(pushOrder.getDetail(),Detail.class);
        pushOrder.setDetailList(details);
        return pushOrder;
    }

    /**
     * 解析推送取消订单的信息
     * @param orderCancelJson
     * @return
     */
    public static OrderCancel getCancelOrder(String orderCancelJson){
        log.info("orderCancelJson:{}",orderCancelJson);
        OrderCancel orderCancel = parseObject(orderCancelJson,OrderCancel.class);
        return orderCancel;
    }

    public static OrderRefund getRefundOrder(String orderRefundJson){
        log.info("orderRefundJson:{}",orderRefundJson);
        return parseObject(orderRefundJson,OrderRefund.class);
    }
    public static ShippingStatus getShippingStatus(String shippingStatusJson){
        return parseObject(shippingStatusJson,ShippingStatus.class);
    }
    public static PoiStatus getPoiStatus(String poiStatusJson){
        return parseObject(poiStatusJson,PoiStatus.class);
    }

    public static List<FoodInfo> getFoodInfoList(String foodInfoJson){
        log.info("foodInfoJson:{}",foodInfoJson);
        List<FoodInfo> foodInfoList = parseArray(foodInfoJson,FoodInfo.class);
        return foodInfoList;
    }


}
