package com.cinsc.meituan.controller;

import com.cinsc.meituan.DTO.FoodInfo;
import com.cinsc.meituan.DTO.MTShop;
import com.cinsc.meituan.DTO.pushBack.*;
import com.cinsc.meituan.dao.ShopRepository;
import com.cinsc.meituan.entity.Shop;
import com.cinsc.meituan.enums.ResultEnum;
import com.cinsc.meituan.exception.MyException;
import com.cinsc.meituan.service.MappingService;
import com.cinsc.meituan.service.OrderService;
import com.cinsc.meituan.service.ShopService;
import com.cinsc.meituan.service.redis.RedisService;
import com.cinsc.meituan.util.JsonUtil;
import com.cinsc.meituan.util.MyUtil;
import com.cinsc.meituan.util.ResultVOUtil;
import com.cinsc.meituan.util.convert.Convert;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/push")//TODO,1.需要进行对推送信息的验证,避免其他平台推送的伪造信息,2.逻辑代码的完善
public class PushController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private MappingService mappingService;
    @Autowired
    private RedisService redisService;

    /**
     * 美团门店绑定回调接口
     * @param request
     * @return
     */
    @RequestMapping("/MTToken")
    public Object pushToken(HttpServletRequest request){
        String appAuthToken = request.getParameter("appAuthToken");
        String ePoiId = request.getParameter("ePoiId");
        String poiId = request.getParameter("poiId");
        String poiName = request.getParameter("poiName");
        //TODO,门店绑定后的逻辑操作..
//        String userId = redisService.getKey(ePoiId);
//        if (userId==null){
//            log.error("当前时间:{},缓存失效..",new Date());
//            throw new MyException(ResultEnum.REDIS_KEY_OUT_OF_TIME);
//        }
//        mappingService.mappingAllDishes(appAuthToken,ePoiId);
//        shopService.saveBaseInfo(appAuthToken,ePoiId, Long.parseLong(userId),"美团");
        Map map = new HashMap();
        map.put("data","success");
        return map;
    }


    /**
     * 解绑门店后推送接口，
     * 解绑后删除一些门店信息
     * @return
     */
    @RequestMapping("/releaseBinding")
    public Object pushReleaseBinding(HttpServletRequest request){
        String ePoiId = request.getParameter("ePoiId");
        //TODO，对门店的逻辑操作
        log.info("解绑门店操作");
        shopRepository.deleteById(Long.valueOf(ePoiId));
        Map map = new HashMap();
        map.put("data","success");
        return map;
    }

    @RequestMapping("/MTOrder")
    public Object pushOrder(HttpServletRequest request){
        String ePoiId = request.getParameter("ePoiId");
        PushOrder pushOrder = JsonUtil.getPushOrder(request.getParameter("order"));
        log.info("ePoiId:{},pushOrder:{}",ePoiId,pushOrder.toString());
        //判断商户是否自动接单

        //如果自动接单
        //1.进行订单数据的存储

        //2.调用确认订单的接口
        Object re = orderService.orderConfirm(MyUtil.appAuthToken,pushOrder.getOrderId());
        log.info("自动接单操作:{}",re);
        //3.判断商户是否接通打印机

        //4.打印订单信息

        //如果商户没有设置自动接单就推送给用户等待用户进行接单请求
        //发送APP推送消息
        //在指定时间内如果商家确认接单则调用接单接口
        //在指定时间内没有收到用户的确认接单请求,系统自动调用取消订单
        return ResultVOUtil.pushSuccess();
    }

    @RequestMapping("/MTOrderCancel")
    public Object pushOrderCancel(HttpServletRequest request){
        String ePoiId = request.getParameter("ePoiId");
        //订单取消后推送订单取消信息到app
        OrderCancel orderCancel = JsonUtil.getCancelOrder(request.getParameter("orderCancel"));
        log.info("ePoiId:{},取消订单信息:{}",ePoiId,orderCancel.toString());

        //逻辑代码
        return ResultVOUtil.pushSuccess();
    }

    @RequestMapping("/MTOrderRefund")
    public Object pushOrderRefund(HttpServletRequest request){
        String ePoiId = request.getParameter("ePoiId");
        OrderRefund orderRefund = JsonUtil.getRefundOrder(request.getParameter("orderRefund"));
        log.info("门店:{},退款信息:{}",ePoiId,orderRefund);

        return ResultVOUtil.pushSuccess();
    }

    @RequestMapping("/MTOrderHadCancel")
    public Object pushOrderHadCancel(HttpServletRequest request){
        String ePoiId = request.getParameter("ePoiId");
        PushOrder pushOrder = JsonUtil.getPushOrder(request.getParameter("order"));
        log.info("订单已被取消...门店:{},订单:{}",ePoiId,pushOrder.toString());

        return ResultVOUtil.pushSuccess();
    }

    @RequestMapping("/MTOrderFinish")
    public Object pushOrderFinish(HttpServletRequest request){
        String ePoiId = request.getParameter("ePoiId");
        PushOrder pushOrder = JsonUtil.getPushOrder(request.getParameter("order"));
        log.info("订单完结....门店:{},订单:{}",ePoiId,pushOrder.toString());

        return ResultVOUtil.pushSuccess();
    }

    @RequestMapping("/MTShippingStatus")
    public Object pushShippingStatus(HttpServletRequest request){
        String ePoiId = request.getParameter("ePoiId");
        ShippingStatus shippingStatus = JsonUtil.getShippingStatus(request.getParameter("shippingStatus"));
        log.info("门店:{},配送状态:{}",ePoiId,shippingStatus.toString());
        return ResultVOUtil.pushSuccess();
    }

    @RequestMapping("/MTPoiStatus")
    public Object pushPoiStatus(HttpServletRequest request){
        String ePoiId = request.getParameter("ePoiId");
        PoiStatus poiStatus = JsonUtil.getPoiStatus(request.getParameter("poiStatus"));
        log.info("门店:{},门店状态信息:{}",ePoiId,poiStatus.toString());
        return ResultVOUtil.pushSuccess();

    }

    @RequestMapping("/MTPartRefund")
    public Object pushPartRefund(HttpServletRequest request){
        String ePoiId = request.getParameter("ePoiId");
        String money = request.getParameter("money");
        String notifyType = request.getParameter("notifyType");
        long orderId = Long.parseLong(request.getParameter("orderId"));
        String reason = request.getParameter("reason");
        String foodInfoJson = request.getParameter("food");
        List<FoodInfo> foodInfoList = JsonUtil.getFoodInfoList(foodInfoJson);
        log.info("ePoiId:{},money:{},notifyType:{},orderId:{},reason:{}",ePoiId,money,notifyType,reason);
        log.info("foodInfoList:{}",foodInfoList.toString());


        return ResultVOUtil.pushSuccess();
    }

}
