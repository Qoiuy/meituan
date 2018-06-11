package com.cinsc.meituan.DTO;

import lombok.Data;

@Data
public class MTShop {
    private String ePoiId;
    private String name;
    private String noticeInfo;//门店公告信息
    private String address;
    private String tagName;//美团方品类名称
    private String latitude;//门店经度
    private String longitude;//门店纬度
    private String phone;
    private String pictureUrl;//门店图片Url
    private Integer isOpen;//是否营业:1可配送 3休息中 0-未上线
    private Integer isOnline;//是否在线:1-上线 0-下线
    private String openTime;
    private float shippingFee;//配送费
    private Integer invoiceSupport;//是否支持开发票
    private Integer invoiceMinPrice;//开发票的最小订单价
    private String invoiceDescription;//发票相关说明
    private Integer preBook;//是否支持预下单:0-不支持,1 表示支持
    private Integer preBookMinDays;//预下单最小日期:0-从当天算起
    private Integer preBookMaxDays;//预下单最大日期:preBookMinDays =0，preBookMaxDays=1，表示支持当天，明天的预下单
    private Integer timeSelect;//是否支持营业时间内预下单:0-不支持,1 表示支持
}
