package com.cinsc.meituan.service.serviceImpl;

import com.cinsc.meituan.DTO.Detail;
import com.cinsc.meituan.DTO.Order;
import com.cinsc.meituan.service.OrderService;
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

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class OrderServiceImplTest {
    @Autowired
    private OrderService orderService;

    //测试成功,解析json创建Order对象
    @Test
    public void queryById() {
        Order order =  orderService.queryById(MyUtil.appAuthToken,28982351238200904L);
        log.info("order's details:{}",order.getDetail().toString());

    }

    @Test
    public void queryByDaySeq() {
        String re = (String) orderService.queryByDaySeq(MyUtil.appAuthToken,1,"1212zw_19900002312");
        log.info("re:{}",re);
    }

    @Test
    public void orderQueryByePoids() {
        String re = (String) orderService.orderQueryByePoids(MyUtil.appAuthToken,"1212zw_19900002312,");
        log.info("re:{}",re);
    }

    @Test
    public void orderQueryByDevId() {
    }

    @Test
    public void orderConfirm() {
        Object re = orderService.orderConfirm(MyUtil.appAuthToken,28982351796175306L);
        log.info("result:{}",re);

    }

    @Test
    public void orderCancel() {
        String re = (String) orderService.orderCancel(MyUtil.appAuthToken,10018329670L,"1","没空");
        log.info("re:{}",re);
    }

    @Test
    public void agreeRefund() {
    }

    @Test
    public void rejectRefund() {
    }

    @Test
    public void queryPartRefundFoods() {
    }

    @Test
    public void applyPartRefund() {
    }
}