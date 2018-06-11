package com.cinsc.meituan.DTO;

import lombok.Data;

import java.util.List;


@Data
public class Order {
    private long cTime;//创建时间
    private String caution;
    private long cityId;//城市Id
    private long deliveryTime;
    private String detail;//订单详情,其中包括有哪些菜品
    private List<Detail> details;
    private String ePoiId;
    private String extras;//扩展信息
    private Integer hasInvoiced;//是否需要发票;
    private String invoiceTitle;//发票抬头
    private String taxpayerId;//发票税号
    private Integer isThirdShipping;//是否第三方配送0否1是
    private double latitude;//实际送餐地址维度
    private double longitude;//实际送餐地址经度
    private String poiReceiveDetail;//商家对账信息
    private String logisticsCode;//配送类型码
    private long logisticsCompletedTime;//配送完成时间
    private long logisticsConfirmTime;//配送确认时间,骑手接单时间
    private long logisticsDispatcherMobile;//骑手电话
    private String logisticsDispatcherName;//骑手姓名
    private long logisticsFetchTime;//骑手取单时间
    private Integer logisticsId;//配送方Id
    private String logisticsName;//配送方名称
    private long logisticsSendTime;//配送单下单时间
    private Integer logisticsStatus;//配送订单状态code,参配送状态码
    private long orderCompletedTime;//订单完成时间
    private long orderConfirmTime;//商户确认时间
    private long orderCancelTime;//订单取消时间
    private long orderId;
    private long orderIdView;//订单展示Id,指C端用户在外卖APP上看到的订单号
    private long orderSendTime;//用户下单时间
    private float originalPrice;//订单原价
    private Integer payType;//支付类型1.货到付款2.在线付款
    private String poiAddress;//门店地址
    private long poiId;//外卖中的门店Id
    private String poiName;
    private String poiPhone;
    private String recipientAddress;
    private String recipientName;
    private String recipientPhone;
    private String shipperPhone;//骑手电话
    private float shippingFee;//配送费
    private Integer status;//订单状态
    private float total;//总价
    private long uTime;//更新时间
    private Integer daySeq;//当天订单的流水号
    private Integer dinnersNumber;//就餐人数,根据就餐人数来搭配餐具
    private Integer pickType;//0:普通取餐1:到店取餐

    @Override
    public String toString() {
        return "{" +
                "cTime:" + cTime +
                ", caution:'" + caution + '\'' +
                ", cityId:"+cityId+'\''+
                ", deliveryTime:" + deliveryTime +
                ", detail:'" + detail + '\'' +
                ", ePoiId:'" + ePoiId + '\'' +
                ", extras:'" + extras + '\'' +
                ", hasInvoiced:" + hasInvoiced +
                ", invoiceTitle:'" + invoiceTitle + '\'' +
                ", taxpayerId:'" + taxpayerId + '\'' +
                ", isThirdShipping:" + isThirdShipping +
                ", latitude:" + latitude +
                ", longitude:" + longitude +
                ", poiReceiveDetail:'" + poiReceiveDetail + '\'' +
                ", logisticsCode:'" + logisticsCode + '\'' +
                ", logisticsCompletedTime:" + logisticsCompletedTime +
                ", logisticsConfirmTime:" + logisticsConfirmTime +
                ", logisticsDispatcherMobile:" + logisticsDispatcherMobile +
                ", logisticsDispatcherName:'" + logisticsDispatcherName + '\'' +
                ", logisticsFetchTime:" + logisticsFetchTime +
                ", logisticsId:" + logisticsId +
                ", logisticsName:'" + logisticsName + '\'' +
                ", logisticsSendTime:" + logisticsSendTime +
                ", logisticsStatus:" + logisticsStatus +
                ", orderCompletedTime:" + orderCompletedTime +
                ", orderConfirmTime:" + orderConfirmTime +
                ", orderCancelTime:" + orderCancelTime +
                ", orderId:" + orderId +
                ", orderIdView:" + orderIdView +
                ", orderSendTime:" + orderSendTime +
                ", originalPrice:" + originalPrice +
                ", payType:" + payType +
                ", poiAddress:'" + poiAddress + '\'' +
                ", poiId:" + poiId +
                ", poiName:'" + poiName + '\'' +
                ", poiPhone:'" + poiPhone + '\'' +
                ", recipientAddress:'" + recipientAddress + '\'' +
                ", recipientName:'" + recipientName + '\'' +
                ", recipientPhone:'" + recipientPhone + '\'' +
                ", shipperPhone:'" + shipperPhone + '\'' +
                ", shippingFee:" + shippingFee +
                ", status:" + status +
                ", total:" + total +
                ", uTime:" + uTime +
                ", daySeq:" + daySeq +
                ", dinnersNumber:" + dinnersNumber +
                ", pickType:" + pickType +
                '}';
    }
}
