package com.cinsc.meituan.service;

import com.cinsc.meituan.DTO.Order;

public interface OrderService {
    Order queryById(String token, long orderId);
    Object queryByDaySeq(String token,Integer daySeq,String ePoiId);//根据当天的订单流水号进行查询
    Object orderConfirm(String token,long orderId);
    Object orderCancel(String token, long orderId, String reasonCode, String reason);//reasonCode取消原因码(自定义)
    Object agreeRefund(String token,long orderId,String reason);
    Object rejectRefund(String token,long orderId,String reason);
    Object orderQueryByePoids(String token, String epoiIds);//查询5分钟内待确认的订单
    Object orderQueryByDevId(Integer developerId,long maxOffsetId,Integer size);//通过开发者Id查询五分钟内待确认的订单
    Object queryPartRefundFoods(String token,long orderId);//查询可以进行部分退款的菜品
    Object applyPartRefund(String token,long orderId,String foodData);//申请部分退款

}
