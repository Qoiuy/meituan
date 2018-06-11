package com.cinsc.meituan.service;

import com.cinsc.meituan.DTO.MTShop;

public interface ShopService {
    public Object saveBaseInfo(String token, String ePoiId, long userId,String platform);//初始门店映射,存储菜品,分类,门店,订单信息
    public Object setOpen(String token);//设置门店营业
    public Object setClose(String token);//设置休息
    public Object updateOpenTime(String token,String openTime);//设置或修改营业时间
    public Object queryDelayDispatch(String token);//查询是否延迟发配送
    public Object updateDelayDispatch(String token,Integer delaySeconds);//设置延迟时间,delaySeconds该参数需在300-600秒之间
    public Object queryPoiInfo(String token,String ePoiIds);//ePoiIds半角逗号分隔
    public MTShop getShopInfoByePoiId(String token, String ePoiId);
    public com.cinsc.meituan.entity.Shop getDBShopInfo(String ePoiId);
    public Object getReleaseBindingUrl(String ePoiId);
    public Object autoConfirmOrder();
    public Object cancelAutoConfirmOrder();
}
