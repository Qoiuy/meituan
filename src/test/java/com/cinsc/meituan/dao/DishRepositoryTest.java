package com.cinsc.meituan.dao;

import com.cinsc.meituan.entity.Dish;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class DishRepositoryTest {
    @Autowired
    private DishRepository dishRepository;

    @Test
    public void save(){
        Dish dish = new Dish();
        dish.setBoxNum(10);
        dish.setBox_price(BigDecimal.valueOf(12));
        dish.setCategoryId(123546789);
        dish.setDescription("好吃的菜");
        dish.setDishName("麻油抄手");
        dish.setIsSoldout(0);
        dish.setMinOrdercount(1);
        dish.setPicture("/sbjk/csnk/a.jpg");
        dish.setPrice(BigDecimal.valueOf(12));
        dish.setSequence(1);
        dish.setUnit("份");
        log.info(dishRepository.save(dish).toString());
    }
}