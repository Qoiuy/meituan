package com.cinsc.meituan.controller;

import com.cinsc.meituan.DTO.MTDish;
import com.cinsc.meituan.dao.DishRepository;
import com.cinsc.meituan.dao.ShopRepository;
import com.cinsc.meituan.entity.Dish;
import com.cinsc.meituan.entity.Shop;
import com.cinsc.meituan.entity.User;
import com.cinsc.meituan.enums.ResultEnum;
import com.cinsc.meituan.service.*;
import com.cinsc.meituan.util.MyUtil;
import com.cinsc.meituan.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@Slf4j
public class MTShopController {

    @Autowired
    private ShopService shopService;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private DishRepository dishRepository;
    @Autowired
    private DishService dishService;
    @Autowired
    private MappingService mappingService;
    @Autowired
    private UserService userService;
    @Autowired
    ImageService imageService;

    @RequestMapping("/mappingAllDishes")
    public Object mappingAllDishes(long shopId){
        Shop shop = shopRepository.findByShopId(shopId);
        return mappingService.mappingAllDishes(shop.getAppAuthToken(), String.valueOf(shop.getShopId()));
    }

    @RequestMapping("/uploadImage")
    public Object uploadDishImage(@RequestParam("file") MultipartFile file,long shopId,long dishId) {
        if (file.isEmpty()) {
            return ResultVOUtil.error(ResultEnum.IMAGE_IS_EMPTY);
        }
        Shop shop = shopRepository.findByShopId(shopId);
        Dish dish = dishRepository.findByDishId(dishId);
        MTDish mtDish = dishService.getDishByDishId(shop.getAppAuthToken(),String.valueOf(shopId),String.valueOf(dishId));
        mtDish.setEPoiId(String.valueOf(shopId));
        Object result;
        try {
            byte[] bytes = file.getBytes();
            //TODO,部署服务器需要对文件保存路径进行修改
            String imagePath = MyUtil.uploadPath + file.getOriginalFilename();
            Path path = Paths.get(imagePath);
            Files.write(path, bytes);//写入到本地服务器中的文件夹中
            result = imageService.updateDishImage(shop.getAppAuthToken(), mtDish,new File(imagePath));
            MTDish updateDish = dishService.getDishByDishId(shop.getAppAuthToken(),String.valueOf(shopId),String.valueOf(dishId));
            String picUrl = updateDish.getPicture();
            if(picUrl==null||result==null){
                return ResultVOUtil.error(ResultEnum.UPLOAD_IMAGE_FAIL);
            }
            dish.setPicture(picUrl);
            dishRepository.save(dish);//更新数据库的菜品信息
        } catch (IOException e) {
            log.error("上传服务器菜品图片失败");
            return ResultVOUtil.error(ResultEnum.UPLOAD_IMAGE_FAIL);
        }
        return ResultVOUtil.success(dish);
    }

    @RequestMapping("/getAllMTDishesInfo")
    public Object getAllMTDishesInfo(long shopId){
        Shop shop = shopRepository.findByShopId(shopId);
        User user = userService.getCurrentUser();
        if (user.getUserId()!=shop.getUserId()){
            return ResultVOUtil.error(ResultEnum.PERMISSION_OVER);
        }
        List<MTDish> mtDishList = dishService.getMTDishByePoiId(shop.getAppAuthToken(), String.valueOf(shop.getShopId()));
        return ResultVOUtil.success(mtDishList);
    }

    @RequestMapping("/updateDishInfo")//TODO,同一制定提交菜品表单的格式,注解哪些信息时必须的
    public Object updateDishInfo(long shopId){
        return null;
    }

    @RequestMapping("/deleteDish")
    public Object deleteDish( ){
        return null;
    }

}
