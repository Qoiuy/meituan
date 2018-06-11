package com.cinsc.meituan.util.convert;

import com.cinsc.meituan.DTO.*;
import com.cinsc.meituan.entity.*;
import com.cinsc.meituan.entity.Category;
import com.cinsc.meituan.util.MyUtil;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
public class Convert {

    /**
     * 将DTO对象的DishList转换成Entity对象的DishList
     * 用于存入数据库
     * @param dishes
     * @return
     */
    public static List<Dish> convertDishes(List<MTDish> dishes){
        List<Dish> dishList = new ArrayList<>();
        for (MTDish dishDTO:dishes){
            Dish dish = new Dish();
            dish.setDishId(Long.parseLong(dishDTO.getEDishCode()));
            log.info("dishId:{}",dishDTO.getDishId());
            dish.setBoxNum(dishDTO.getBoxNum());
            dish.setBox_price(BigDecimal.valueOf(dishDTO.getBoxPrice()));
//            dish.setCategoryId();需要先进行菜品分类的数据库存储才能进行分类ID的设置
            dish.setDescription(dishDTO.getDescription());
            dish.setDishName(dishDTO.getDishName());
            dish.setIsSoldout(dishDTO.getIsSoldOut());
            dish.setMinOrdercount(dishDTO.getMinOrderCount());
            dish.setPicture(dishDTO.getPicture());
            dish.setPrice(BigDecimal.valueOf(dishDTO.getPrice()));
            dish.setSequence(Integer.valueOf(dishDTO.getSequence()));
            dish.setUnit(dishDTO.getUnit());
            dishList.add(dish);
        }
        return dishList;
    }

    /**
     * 转换CategoryDTO得到categoryList
     * @param categories
     * @return
     */
    public static List<Category> convertCategory(List<com.cinsc.meituan.DTO.Category> categories){
        List<Category> categoryList = new ArrayList<>();
        for (com.cinsc.meituan.DTO.Category categoryDTO : categories){
            Category category = new Category();
            category.setCategoryName(categoryDTO.getName());
            category.setSequence(categoryDTO.getSequence());
            categoryList.add(category);
        }
        return categoryList;
    }

    /**
     * 转换某个菜品下的所有Sku得到skuList
     * @param dish
     * @return
     */
    public static List<Sku> convertSkus(MTDish dish){
        List<Sku> skuList = new ArrayList<>();
        for (MTSku skuDTO:dish.getSkus()){
            Sku sku = new Sku();
            sku.setSkuId(Long.parseLong(MyUtil.getUniqueKey()));
            sku.setDishId(Long.parseLong(dish.getEDishCode()));
            sku.setSpec(skuDTO.getSpec());
            sku.setStock(skuDTO.getStock());
            sku.setPrice(BigDecimal.valueOf(skuDTO.getPrice()));
            skuList.add(sku);
        }
        return skuList;
    }

    /**
     * 转换多个订单信息列表
     * @param orders
     * @return
     */
    public static List<OrderMaster> convertOrders(List<Order> orders){
        List<OrderMaster> orderMasters = new ArrayList<>();
        for (Order order:orders){
            orderMasters.add(convertOrder(order));
        }
        return orderMasters;
    }

    /**
     * 转换订单信息
     * @param order
     * @return
     */
    public static OrderMaster convertOrder(Order order){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId(String.valueOf(order.getOrderId()));
        orderMaster.setCustomerName(order.getRecipientName());
        orderMaster.setSendAddress(order.getRecipientAddress());
        orderMaster.setCustomerTelephone(Long.parseLong(order.getRecipientPhone()));
        orderMaster.setDeliverFee(BigDecimal.valueOf(order.getShippingFee()));
        orderMaster.setShopId(Long.parseLong(order.getEPoiId()));
        orderMaster.setCaution(order.getCaution());
        orderMaster.setTotalPrice(BigDecimal.valueOf(order.getTotal()));
        orderMaster.setOrderStatus(order.getStatus());
        orderMaster.setPayStatus(0);
        orderMaster.setCreateTime(new Date());
        orderMaster.setUpdateTime(new Date());
        return orderMaster;
    }

    /**
     * 转换订单详情信息列表
     * @param order
     * @return
     */
    public List<OrderDetail> convertOrderDetail(Order order){
        List<OrderDetail> orderDetails = new ArrayList<>();
        for (Detail detail:order.getDetails()){
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(String.valueOf(order.getOrderId()));
            orderDetail.setDishId(Long.parseLong(detail.getApp_food_code()));
            orderDetail.setBoxNum(detail.getBox_num());
            orderDetail.setBoxPrice(BigDecimal.valueOf(detail.getBox_price()));
            orderDetail.setDishName(detail.getFood_name());
            orderDetail.setPrice(BigDecimal.valueOf(detail.getPrice()));
            orderDetail.setSkuId(Long.parseLong(detail.getSku_id()));
            orderDetail.setQuantity(detail.getQuantity());
            orderDetail.setUnit(detail.getUnit());
            orderDetail.setCreateTime(new Date());
            orderDetail.setUpdateTime(new Date());
            orderDetails.add(orderDetail);
        }
        return orderDetails;
    }

    public static Shop convertShop(MTShop shopDTO){
        Shop shop = new Shop();
        shop.setPlatform("美团");
        shop.setShopId(Long.parseLong(shopDTO.getEPoiId()));
        shop.setIsOpen(shopDTO.getIsOpen());
        shop.setName(shopDTO.getName());
        shop.setOpenTime(shopDTO.getOpenTime());
        shop.setTelephone(Long.parseLong(shopDTO.getPhone()));
        return shop;
    }

}
