package com.cinsc.meituan.service.serviceImpl;

import com.cinsc.meituan.DTO.MTDish;
import com.cinsc.meituan.DTO.DishMapping;
import com.cinsc.meituan.DTO.MTSku;
import com.cinsc.meituan.DTO.WaiMaiDishSkuMapping;
import com.cinsc.meituan.dao.DishRepository;
import com.cinsc.meituan.enums.ResultEnum;
import com.cinsc.meituan.service.DishService;
import com.cinsc.meituan.service.MappingService;
import com.cinsc.meituan.util.MyUtil;
import com.cinsc.meituan.util.ResultVOUtil;
import com.sankuai.sjst.platform.developer.request.CipCaterTakeoutDishMapRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MappingServiceImpl implements MappingService {

    @Autowired
    private DishService dishService;
    @Autowired
    private DishRepository dishRepository;
    /**
     * 对菜品进行映射
     * @param token
     * @param ePoiId
     * @param dishMappings
     * @return
     */
    @Override
    public Object dishesMapping(String token, String ePoiId, String dishMappings) {
        CipCaterTakeoutDishMapRequest request = new CipCaterTakeoutDishMapRequest();
        request.setRequestSysParams(MyUtil.getParams(token));
        request.setePoiId(ePoiId);
        request.setDishMappings(dishMappings);
        try {
            return request.doRequest();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return ResultVOUtil.error(ResultEnum.MAPPING_DISH_FAIL);
    }
    /**
     * 一键式映射门店下所有菜品
     * 映射需要传入的参数:eDishCode,dishId,eDishSkuCode,dishSkuId.
     * @param token
     * @param ePoiId
     * @return
     */
    @Override
    public Object mappingAllDishes(String token, String ePoiId) {
        //查出所有baseDishes,进行菜品映射,并存入菜品信息
        List<MTDish> dishes = dishService.queryDishesByePoiId(token,ePoiId);
        //判断是否有eDishCode,从而判断该菜品是否映射
        List<DishMapping> dishMappings = new ArrayList<>();
        for (MTDish dish:dishes){
            log.info("执行映射菜品循环遍历");
            if (dish.getEDishCode().equals("")){
                DishMapping dishMapping = new DishMapping();
                dishMapping.setDishId(dish.getDishId());
//                dishMapping.setEDishCode();
                //使用工具类生成eDishCode和skuId,设置菜品映射的eDishCode,
                String eDishCode = MyUtil.getUniqueKey();

                dish.setEDishCode(eDishCode);
                dishMapping.setEDishCode(eDishCode);
                List<WaiMaiDishSkuMapping> waiMaiDishSkuMappings = new ArrayList<>();
                //遍历sku进行映射的添加
                for (MTSku sku:dish.getSkus()){
                    log.info("sku:{}",sku.toString());
                    WaiMaiDishSkuMapping waiMaiDishSkuMapping = new WaiMaiDishSkuMapping();
                    waiMaiDishSkuMapping.setDishSkuId(Long.parseLong(sku.getSkuId()));//设置美团方需要进行映射的skuId
                    sku.setSkuId(MyUtil.getUniqueKey());//设置ERP方的菜品规格Id
                    //转换sku对象存入数据库//TODO
                    waiMaiDishSkuMapping.setEDishSkuCode(sku.getSkuId());//设置映射的Id
                    waiMaiDishSkuMappings.add(waiMaiDishSkuMapping);
                }
                dishMapping.setWaiMaiDishSkuMappings(waiMaiDishSkuMappings);
                dishMappings.add(dishMapping);
            }

        }
        return dishesMapping(token,ePoiId,dishMappings.toString());
    }

}
