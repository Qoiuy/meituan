package com.cinsc.meituan.service.serviceImpl;

import com.cinsc.meituan.DTO.MTDish;
import com.cinsc.meituan.DTO.MTSku;
import com.cinsc.meituan.service.ImageService;
import com.cinsc.meituan.util.MyUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ImageServiceImplTest {

    @Autowired
    private ImageService imageService;

    //测试成功
    @Test
    public void upLoadImage() {
        File file = new File("/home/hk/图片/timg.jpeg");
        String  path =  imageService.upLoadImage(MyUtil.appAuthToken,"1212zw_19900002312","test.jpg",file);
        log.info("path:{}",path);
    }

    /**
     * 菜品更新时需要参数:
     * ePoiId,categoryName,dishName,eDishCode,Description
     * skuId,spec,stock,skuPrice
     */
    @Test
    public void updateDishImage(){
        File file = new File("//home//hk//图片//talk1.jpg");
        MTDish dish = new MTDish();
        dish.setEPoiId("1212zw_19900002312");
        dish.setCategoryName("主食");
        dish.setDishName("四川火锅");
        dish.setEDishCode("2");
        dish.setDescription("正宗的四川火锅");
        MTSku sku = new MTSku();
        sku.setSkuId("huoguo_daguo");
        sku.setSpec("大份");
        sku.setStock(10);
        sku.setPrice(120);
        List skus = new ArrayList();
        skus.add(sku);
        dish.setSkus(skus);
        Object re = imageService.updateDishImage(MyUtil.appAuthToken,dish,file);
        log.info("re:{}",re);
    }

}