package com.cinsc.meituan.service.serviceImpl;

import com.cinsc.meituan.service.CategoryService;
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
public class CategoryServiceImplTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void addCat(){
        Object result = categoryService.addCat(MyUtil.appAuthToken,1,"主食");
        log.info("result:{}",result);
    }
    @Test
    public void updateCat() {
        Object result = categoryService.updateCat(MyUtil.appAuthToken,"主食","甜品");
        log.info("Object:{}",result);
    }
    @Test
    public void queryCatList(){
        List catList =  categoryService.queryCatList(MyUtil.appAuthToken);
        log.info("catList:{}",catList.toString());
//        //测试菜品分类解析的json成功
//        List categories = JsonUtil.getCatJson(re);
//        for (int i=0;i<categories.size();i++){
//            Map map = (Map) categories.get(i);
//            log.info(map.toString());
//            log.info("ePoiId:{}",map.get("ePoiId").toString());
//        }
    }
    @Test
    public void deleteCat(){
        Object result = categoryService.deleteCat(MyUtil.appAuthToken,"甜品");
    }
}