package com.cinsc.meituan.DTO.pushBack;

import com.cinsc.meituan.DTO.Detail;
import lombok.Data;

import java.util.List;

@Data
public class PushOrder {
    private long orderId;//订单Id
    private long orderIdView;//订单展示Id
    private String caution;
    private long cityId;//城市Id
    private long ctime;//订单创建时间
    private long utime;//更新时间
    private String daySeq;//当天的订单流水号
    private long deliveryTime;//用户预计送达时间,单位为秒
    private String detail;//****
    private List<Detail> detailList;
    private String ePoiId;
    private String extras;//****
    private Integer hasInvoiced;
    private String invoiceTitle;//发票抬头
    private String taxpayerId;//发票税号
    private boolean isFavorites;//用户是否收藏此门店
    private boolean isPoiFirstOrder;//用户是否第一次在此门店进行点餐
    private Integer isThirdShipping;//是否第三方配送,0否,1是
    private double latitude;
    private double longitude;
    private Integer logisticsCode;//配送方式码7.6说明
    private double originalPrice;//订单原价
    private Integer payType;//支付类型,1货到付款,2在线支付
    private Integer pickType;//取餐类型
    private String poiReceiveDetail;//对账详情*****
    private String recipientAddress;//收货人地址
    private String recipientName;//收货人姓名
    private String recipientPhone;
    private String shipperPhone;//配送人员电话
    private double shippingFee;
    private Integer status;
    private double total;
    private Integer quantity;
    private float avg_send_time;//餐厅平均送餐时间



}
