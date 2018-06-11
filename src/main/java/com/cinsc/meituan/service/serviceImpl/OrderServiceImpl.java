package com.cinsc.meituan.service.serviceImpl;

import com.cinsc.meituan.DTO.Detail;
import com.cinsc.meituan.DTO.Order;
import com.cinsc.meituan.enums.ResultEnum;
import com.cinsc.meituan.service.OrderService;
import com.cinsc.meituan.util.JsonUtil;
import com.cinsc.meituan.util.MyUtil;
import com.cinsc.meituan.util.ResultVOUtil;
import com.sankuai.sjst.platform.developer.request.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Override
    public Order queryById(String token, long orderId) {
        CipCaterTakeoutOrderQueryByIdRequest request = new CipCaterTakeoutOrderQueryByIdRequest();
        request.setRequestSysParams(MyUtil.getParams(token));
        request.setOrderId(orderId);
        try {
            String re =  request.doRequest();
            Order order = JsonUtil.getOrderJson(re);
            log.info("orderInfo:{}",order.getDetail());
            List detailList = JsonUtil.parseObjectListKeyMaps(order.getDetail());
            List<Detail> details = new ArrayList<>();
            order.setDetails(details);
            for (int i=0;i<detailList.size();i++){
                Map map = (Map) detailList.get(i);
                Detail detail = new Detail();
                detail.setApp_food_code((String) map.get("app_food_code"));
                detail.setBox_num((Integer) map.get("box_num"));
                detail.setBox_price(Float.parseFloat(map.get("box_price").toString()));
                detail.setCart_id((Integer) map.get("cart_id"));
                detail.setFood_discount(Float.parseFloat(map.get("food_discount").toString()));
                detail.setFood_name((String) map.get("food_name"));
                detail.setSpec(String.valueOf(map.get("spec")));
                detail.setFood_property((String) map.get("food_property"));
                detail.setPrice(Float.parseFloat(map.get("price").toString()));
                detail.setQuantity((Integer) map.get("quantity"));
                detail.setSku_id((String) map.get("sku_id"));
                //注:美团文档错误,返回的Json中的detail有spec但是文档中没有写出//TODO
                detail.setUnit((String) map.get("unit"));
                details.add(detail);
            }
            return order;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询当日指定流水号的订单信息
     * @param token
     * @param daySeq
     * @param ePoiId
     * @return
     */
    @Override
    public Object queryByDaySeq(String token, Integer daySeq ,String ePoiId) {
        CipCaterTakeoutOrderQueryByDaySeqRequest request = new CipCaterTakeoutOrderQueryByDaySeqRequest();
        request.setRequestSysParams(MyUtil.getParams(token));
        request.setDaySeq(daySeq);
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
     * 根据门店Id对查询5分钟内未确认的订单.
     * 门店号用逗号隔开.
     * @param token
     * @param ePoiIds
     * @return
     */
    @Override
    public Object orderQueryByePoids(String token, String ePoiIds) {
        CipCaterTakeoutOrderPullByEpoiIdsRequest request = new CipCaterTakeoutOrderPullByEpoiIdsRequest();
        request.setRequestSysParams(MyUtil.getParams(token));
        request.setEpoiIds(ePoiIds);
        try {
            return request.doRequest();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    //未找到该接口,可以自己进行业务逻辑的处理
    @Override
    public Object orderQueryByDevId(Integer developerId, long maxOffsetId, Integer size) {
        return null;
    }

    @Override
    public Object orderConfirm(String token, long orderId) {
        CipCaterTakeoutOrderConfirmRequest request = new CipCaterTakeoutOrderConfirmRequest();
        request.setRequestSysParams(MyUtil.getParams(token));
        request.setOrderId(orderId);
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
    public Object orderCancel(String token, long orderId, String reasonCode, String reason) {
        CipCaterTakeoutOrderCancelRequest request = new CipCaterTakeoutOrderCancelRequest();
        request.setRequestSysParams(MyUtil.getParams(token));
        request.setOrderId(orderId);
        request.setReason(reason);
        request.setReasonCode(reasonCode);
        try {
            return request.doRequest();
        } catch (IOException e) {
            log.error("IO异常..");
        } catch (URISyntaxException e) {
            log.error("URISyntaxException异常..");
        }
        return ResultVOUtil.error(ResultEnum.ORDER_CANCEL_FAIL);
    }

    /**
     * 同意退款
     * @param token
     * @param orderId
     * @param reason
     * @return
     */
    @Override
    public Object agreeRefund(String token, long orderId, String reason) {
        CipCaterTakeoutOrderRefundAcceptRequest request = new CipCaterTakeoutOrderRefundAcceptRequest();
        request.setRequestSysParams(MyUtil.getParams(token));
        request.setOrderId(orderId);
        request.setReason(reason);
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
     * 拒绝退款
     * @param token
     * @param orderId
     * @param reason
     * @return
     */
    @Override
    public Object rejectRefund(String token, long orderId, String reason) {
        CipCaterTakeoutOrderRefundRejectRequest request = new CipCaterTakeoutOrderRefundRejectRequest();
        request.setRequestSysParams(MyUtil.getParams(token));
        request.setOrderId(orderId);
        request.setReason(reason);
        try {
            return request.doRequest();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    //未找到接口,查询部分退款的菜品,可以自己进行业务逻辑的处理
    @Override
    public Object queryPartRefundFoods(String token, long orderId) {
        return null;
    }

    //未找到接口
    @Override
    public Object applyPartRefund(String token, long orderId, String foodData) {
        return null;
    }
}
