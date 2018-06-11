package com.cinsc.meituan.service.serviceImpl;

import com.cinsc.meituan.DTO.MTDish;
import com.cinsc.meituan.DTO.JsonData;
import com.cinsc.meituan.service.DishService;
import com.cinsc.meituan.service.ImageService;
import com.cinsc.meituan.util.JsonUtil;
import com.cinsc.meituan.util.MyUtil;
import com.sankuai.sjst.platform.developer.request.CipCaterTakeoutImageUploadRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService{
    @Autowired
    private DishService dishService;

    //上传图片返回一个图片的ID用于更新菜品时调用进行绑定菜品
    @Override
    public String upLoadImage(String token, String ePoiId, String imageName, File file) {
        CipCaterTakeoutImageUploadRequest request = new CipCaterTakeoutImageUploadRequest();
        request.setRequestSysParams(MyUtil.getParams(token));
        request.setePoiId(ePoiId);
        request.setImageName(imageName);
        request.setFile(file);
        try {
             return request.doRequest();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 更新菜品的图片
     * @param token
     * @param dish
     * @param file
     * @return
     */
    @Override
    public Object updateDishImage(String token, MTDish dish, File file) {
        String imageData = upLoadImage(token,dish.getEPoiId(),"test.jpg",file);
        JsonData data = JsonUtil.parseObject(imageData,JsonData.class);
        log.info("imageId:{}",data.getData());
        dish.setPicture(data.getData());
        ArrayList dishes = new ArrayList();
        dishes.add(dish);
        return dishService.addDish(token,dish.getEPoiId(),dishes);
    }


}
