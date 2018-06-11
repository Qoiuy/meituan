package com.cinsc.meituan.service.serviceImpl;

import com.cinsc.meituan.DTO.DishMapping;
import com.cinsc.meituan.DTO.WaiMaiDishSkuMapping;
import com.cinsc.meituan.service.MappingService;
import com.cinsc.meituan.util.MyUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class MappingServiceImplTest {
    @Autowired
    MappingService mappingService;

    @Test
    public void storeMapping() {
    }

    //TODO
    //优化菜品映射将其封装成方法
    @Test
    public void dishesMapping() {
        DishMapping dishMapping = new DishMapping();
        dishMapping.setDishId(800921077);
        dishMapping.setEDishCode("yangzhouchaofan");

        WaiMaiDishSkuMapping waiMaiDishSkuMapping = new WaiMaiDishSkuMapping();
        waiMaiDishSkuMapping.setDishSkuId(868299630);
        waiMaiDishSkuMapping.setEDishSkuCode("yangzhouchaofan_xiaofen");
        WaiMaiDishSkuMapping waiMaiDishSkuMapping1 = new WaiMaiDishSkuMapping();
        waiMaiDishSkuMapping1.setDishSkuId(868300518);
        waiMaiDishSkuMapping1.setEDishSkuCode("yangzhoucahofan_dafen");

        List waiMaiDishSkuMappings = new ArrayList();
        waiMaiDishSkuMappings.add(waiMaiDishSkuMapping);
        waiMaiDishSkuMappings.add(waiMaiDishSkuMapping1);

        dishMapping.setWaiMaiDishSkuMappings(waiMaiDishSkuMappings);
        List dishes = new ArrayList();
        dishes.add(dishMapping);

        log.info("dishes:{}",dishes.toString());//注意:toArray输出最外层没有[],toString有[]
        String dishMappings = dishes.toString();
        log.info("dishMappings:{}",dishMappings);
        String  re = (String) mappingService.dishesMapping(MyUtil.appAuthToken,"123456789",dishMappings);
        log.info("result:{}",re);
    }

    @Test
    public void mappingAllDishes(){
        String re = (String) mappingService.mappingAllDishes(MyUtil.appAuthToken,"123456789");
        log.info("re:{}",re);
    }
}