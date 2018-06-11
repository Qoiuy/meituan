package com.cinsc.meituan.service.serviceImpl;

import com.cinsc.meituan.DTO.MTShop;
import com.cinsc.meituan.service.ShopService;
import com.cinsc.meituan.util.JsonUtil;
import com.cinsc.meituan.util.MyUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ShopServiceImplTest {
    @Autowired
    private ShopService shopService;

    @Test
    public void saveBaseInfo(){
        String re = (String) shopService.saveBaseInfo(MyUtil.appAuthToken,MyUtil.ePoiId,1,"美团");
        log.info("re:{}",re);
    }

    @Test
    public void setOpen() {
        Object re = shopService.setOpen(MyUtil.appAuthToken);
        log.info("result:{}",re);
    }

    @Test
    public void setClose() {
        Object re = shopService.setClose(MyUtil.appAuthToken);
        log.info("result:{}",re);
    }

    @Test
    public void updateOpenTime() {
        Object re = shopService.updateOpenTime(MyUtil.appAuthToken,"06:00-13:00,14:00-23:00");
        log.info("result:{}",re);
    }

    @Test
    public void queryDelayDispatch() {
        Object re = shopService.queryDelayDispatch(MyUtil.appAuthToken);
        log.info("result:{}",re);
    }

    @Test
    public void updateDelayDispatch() {
        Object re = shopService.updateDelayDispatch(MyUtil.appAuthToken,400);
        log.info("result:{}",re);
    }

    @Test
    public void queryPoiInfo() {
        String re = (String) shopService.queryPoiInfo(MyUtil.appAuthToken,"1212zw_19900002312");
        List shopInfo = JsonUtil.getShopInfoJson(re);
        for (int i=0;i<shopInfo.size();i++){
            Map map = (Map) shopInfo.get(i);
            log.info("address:{}",map.get("address"));
        }
    }
    @Test
    public void getShopInfoByePoiId(){
        MTShop shop = shopService.getShopInfoByePoiId(MyUtil.appAuthToken,"1212zw_19900002312");
        log.info("shopInfo;{}",shop.toString());
    }
}