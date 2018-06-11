package com.cinsc.meituan.util;

import com.cinsc.meituan.DTO.Order;
import com.cinsc.meituan.service.serviceImpl.OrderServiceImpl;
import com.cinsc.meituan.util.printer.PrinterUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class PrinterUtilTest {

    @Autowired
    private OrderServiceImpl orderService;
    @Test
    public void printTest(){
        Order order = orderService.queryById(MyUtil.appAuthToken,28982351796175306L);
        String re = PrinterUtil.printOrder(PrinterUtil.SN,order);
        log.info("re:{}",re);
    }
}
