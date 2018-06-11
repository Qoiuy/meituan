package com.cinsc.meituan.service.serviceImpl;

import com.cinsc.meituan.DTO.*;
import com.cinsc.meituan.service.DishService;
import com.cinsc.meituan.util.JsonUtil;
import com.cinsc.meituan.util.MyUtil;
import com.cinsc.meituan.util.ParamUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class DishServiceImplTest {

    @Autowired
    private DishService dishService;

    /**
     * 菜品添加测试通过
     * 后续需要完善availableTime对象的处理
     */
    @Test
    public void addDish() {
        ArrayList dishes = new ArrayList<MTDish>();
        MTDish dish = new MTDish();
        dish.setBoxNum(1);
        dish.setBoxPrice(1.0f);
        dish.setCategoryName("凉菜");
        dish.setDescription("一道很好吃的凉菜");
        dish.setDishName("川北凉粉");
        dish.setEDishCode("3");
        dish.setEPoiId("1212zw_19900002312");
        dish.setIsSoldOut(0);
        dish.setMinOrderCount(1);
        dish.setPrice(44.5f);
        dish.setUnit("份");
        dish.setPicture("944962477C09DCA2E225615F37ABF0B0");
        MTSku sku = new MTSku();
        sku.setSkuId("chuanbeiliangfen_xiaofen");
        sku.setPrice(33.5f);
        sku.setStock(10);
        sku.setSpec("小份");
        MTSku sku1 = new MTSku();
        sku1.setSkuId("chuanbeiliangfen_dafen");
        sku1.setPrice(44);
        sku1.setStock(20);
        sku1.setSpec("大份");
        //还需要设置availableTime对象
        // TODO
        List skus = new ArrayList<MTSku>();
        skus.add(sku);
        skus.add(sku1);
        //设置dish中的skus
        dish.setSkus(skus);
        dishes.add(dish);
        log.info("{}",dishes.toArray());
        Object re = dishService.addDish(MyUtil.appAuthToken,MyUtil.ePoiId,dishes);
        log.info("result:{}",re);
    }

    @Test
    public void queryBaseDishesByePoiId(){
        String re = (String) dishService.queryBaseDishesByePoiId("dbe26360771b4c67ee32a5fc62ad8fec34ac26259f7815eaea3cab05c8938aa7d096d32becdfb625e67b4cc77172b502",
                "1526615352730257824");
        List baseDishes = JsonUtil.getBaseDishJson(re);
        for (int i=0;i<baseDishes.size();i++){
            Map map = (Map) baseDishes.get(i);
            log.info("catName:{}",map.get("categoryName"));
            log.info("dishId:{}",map.get("dishId"));
            List baseSkus = JsonUtil.getBaseSkusJson(map.get("waiMaiDishSkuBases").toString());
            log.info("baseSkus:{}",baseSkus);
            for (int j=0;j<baseSkus.size();j++){
                Map map1 = (Map) baseSkus.get(j);
                log.info("dishSkuName:{}",map1.get("dishSkuName"));
            }
        }
        log.info("result:{}",re);
    }

    /**
     * 测试通过后续需要完善对返回的json格式的数据进行读取操作
     * 已完善json格式的解析
     */
    @Test
    public void queryDishesByePoiId(){
        String re = (String) dishService.queryDishesByePoiId(MyUtil.appAuthToken,"1212zw_19900002312",0,100);
        log.info("result:{}",re);
        List dishes = JsonUtil.getDishJson(re);
        String dishParam = ParamUtil.getDishParam(dishes,"3","dishName");
        log.info("paramInfo:{}",dishParam);
        String skuParam = ParamUtil.getSkuParam(dishes,"1524825108061859196","1524825108061471882","spec");
        log.info("skuInfo:{}",skuParam);
       /* for (int i=0;i<dishes.size();i++){
            Map map = (Map) dishes.get(i);
            log.info("result:{}",map.toString());
            log.info("description:{}",map.get("description"));
            log.info("skus:{}",map.get("skus"));
            List skus = JsonUtil.getSkusJson(map.get("skus").toString());
            for (int j=0;j<skus.size();j++){
                Map map1 = (Map) skus.get(j);
                log.info("stock:{}",map1.get("stock"));
            }
        }*/

    }

    @Test
    public void deleteDish(){
        Object re = dishService.deleteDish(MyUtil.appAuthToken,"123456789","1");
        log.info("result:{}",re);
    }

    /**
     * 更新菜品库存
     * 需要完善添加信息时的操作,单独设置对象进行添加较为麻烦
     */
    @Test
    public void updateDishStocks(){
        MTSku sku = new MTSku();
        sku.setStock(88);
        sku.setSkuId("chuanbeiliangfen_xiaofen");
        List skus = new ArrayList();
        skus.add(sku);

        DishSku dishSkuStock = new DishSku();
        dishSkuStock.setEDishCode("3");
        dishSkuStock.setSkus(skus);
        List dishSkuStocks = new ArrayList();
        dishSkuStocks.add(dishSkuStock);

        Object re = dishService.updateStock(MyUtil.appAuthToken,"1212zw_19900002312",dishSkuStocks.toString());
        log.info("result:{}",re);
    }

    /**
     * 更新菜品价格
     * 同更新库存一样,需要进一步完善添加方式
     */
    @Test
    public void updatePrice(){
        MTSku sku = new MTSku();
        sku.setPrice(66.5f);
        sku.setSkuId("chuanbeiliangfen_xiaofen");
        List skus = new ArrayList();
        skus.add(sku);

        DishSku dishSku = new DishSku();
        dishSku.setEDishCode("3");
        dishSku.setSkus(skus);
        List dishSkuPrices = new ArrayList();
        dishSkuPrices.add(dishSku);
        Object re = dishService.updatePrice(MyUtil.appAuthToken,"123456789",dishSkuPrices.toString());
        log.info("result:{}",re);
    }

    /**
     * 注意:删除sku时只有存在多个sku才可以调用deleteSku方法,否则会将菜品一起删除
     */
    @Test
    public void deleteSku(){
        Object re = dishService.deleteSku(MyUtil.appAuthToken,"3","chuanbeiliangfen_xiaofen");
        log.info("result{}",re);
    }

    /**
     * 待简化添加方式
     */
    @Test
    public void updateProperty(){
        Property property = new Property();
        Property property1 = new Property();
        property1.setPropertyName("麻度");
        property.setPropertyName("辣度");

        List values = new ArrayList<String>();
        List values1 = new ArrayList<String>();
        values.add("微辣");
        values.add("特辣");
        values1.add("微麻");
        values1.add("中麻");
        property.setValues(values);
        property1.setValues(values1);

        List properties = new ArrayList();
        properties.add(property);
        properties.add(property1);

        DishProperty dishProperty1 = new DishProperty();
        dishProperty1.setEDishCode("3");
        dishProperty1.setProperties(properties);

        List dishProperty = new ArrayList();
        dishProperty.add(dishProperty1);

        Object re = dishService.updateProperty(MyUtil.appAuthToken,dishProperty);
        log.info("result:{}",re);
    }

    @Test
    public void queryGetBaseDishes(){
        List<MTDish> dishes = dishService.queryDishesByePoiId(MyUtil.appAuthToken,"1212zw_19900002312");
        log.info("dishes:{}",dishes.toString());
    }

    @Test
    public void getDishesByePoiId(){
        List<MTDish> dishes = dishService.getDishesByePoiId("dbe26360771b4c67ee32a5fc62ad8fec34ac26259f7815eaea3cab05c8938aa7d096d32becdfb625e67b4cc77172b502",
                "1526615352730257824",1,100);
        log.info("dishes:{}",dishes.toString());
    }
}