package com.cinsc.meituan.service.serviceImpl;

import com.cinsc.meituan.DTO.Category;
import com.cinsc.meituan.DTO.MTDish;
import com.cinsc.meituan.DTO.MTShop;
import com.cinsc.meituan.dao.*;
import com.cinsc.meituan.entity.*;
import com.cinsc.meituan.enums.ResultEnum;
import com.cinsc.meituan.service.*;
import com.cinsc.meituan.util.ResultVOUtil;
import com.cinsc.meituan.util.convert.Convert;
import com.cinsc.meituan.util.JsonUtil;
import com.cinsc.meituan.util.MyUtil;
import com.sankuai.sjst.platform.developer.request.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ShopServiceImpl implements ShopService {
    @Autowired
    private OrderService orderService;
    @Autowired
    private DishService dishService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private MappingService mappingService;
    @Autowired
    private DishRepository dishRepository;
    @Autowired
    private ShopDishRepository shopDishRepository;
    @Autowired
    private ShopCategoryRepository shopCategoryRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private SkuRepository skuRepository;
    @Autowired
    private UserService userService;

    /**
     * 存入店铺基本数据信息
     * @param token
     * @param ePoiId
     * @param userId
     * @param platform
     * @return
     */
    @Override
    @Transactional
    public Object saveBaseInfo(String token, String ePoiId ,long userId,String platform) {
        MTShop shopDTO = getShopInfoByePoiId(token,ePoiId);
        com.cinsc.meituan.entity.Shop  shop = Convert.convertShop(shopDTO);
        shop.setUserId(userId);
        shop.setPlatform(platform);
        shop.setAppAuthToken(token);
        List<Shop> shopList = shopRepository.findByUserId(shop.getUserId());
        if (shopList.size()==0){
            shop.setIsAutoConfirmOrder(0);//默认第一次不自动接单
        }else {
            Integer isAutoConfirmOrder = shopList.get(0).getIsAutoConfirmOrder();
            shop.setIsAutoConfirmOrder(isAutoConfirmOrder);
        }
        com.cinsc.meituan.entity.Shop poi = shopRepository.save(shop);
        log.info("存入店铺信息:{}",poi.toString());

        //存入菜品分类信息
        List<Category> categories = categoryService.queryCatList(token);
        List<com.cinsc.meituan.entity.Category> categoryList = Convert.convertCategory(categories);
        for (int i=0;i<categoryList.size();i++){
            com.cinsc.meituan.entity.Category category = categoryRepository.save(categoryList.get(i));
            ShopCategory shopCategory = new ShopCategory();
            shopCategory.setCategoryId(category.getCategoryId());
            shopCategory.setShopId(Long.parseLong(ePoiId));
            ShopCategory sc = shopCategoryRepository.save(shopCategory);
            log.info("存入菜品分类:{},和门店菜品分类关系:{}",category.toString(),sc.toString());
        }
        //存入菜品信息
        List<MTDish> dishes = dishService.getDishesByePoiId(token,ePoiId,0,100);
        List<com.cinsc.meituan.entity.Dish> dishList = Convert.convertDishes(dishes);
        for (int i=0;i<dishList.size();i++){
            String catName = dishes.get(i).getCategoryName();//从返回的json中查出对应的分类名称
            com.cinsc.meituan.entity.Category category = categoryRepository.findByCategoryName(catName);
            dishList.get(i).setCategoryId(category.getCategoryId());//设置对应分类的ID
            com.cinsc.meituan.entity.Dish dish = dishRepository.save(dishList.get(i));
            ShopDish shopDish = new ShopDish();
            shopDish.setDishId(dish.getDishId());
            shopDish.setShopId(Long.parseLong(ePoiId));
            ShopDish sd = shopDishRepository.save(shopDish);
            log.info("存入菜品:{},和门店菜品关系:{}",dish.toString(),sd.toString());
        }
        //存入菜品规格信息
        for (int i=0;i<dishes.size();i++){
            List<Sku> skuList = Convert.convertSkus(dishes.get(i));
            for (int j=0;j<skuList.size();j++){
                Sku sku = skuRepository.save(skuList.get(j));
                log.info("存入出品规格:{}",sku.toString());
            }
        }


        //美团之前的订单信息无法存储,忽略
        return "success";
    }

    @Override
    public Object setOpen(String token) {
        CipCaterTakeoutPoiOpenRequest request = new CipCaterTakeoutPoiOpenRequest();
        request.setRequestSysParams(MyUtil.getParams(token));
        try {
            return request.doRequest();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object setClose(String token) {
        CipCaterTakeoutPoiCloseRequest request = new CipCaterTakeoutPoiCloseRequest();
        request.setRequestSysParams(MyUtil.getParams(token));
        try {
            return request.doRequest();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object updateOpenTime(String token, String openTime) {
        CipCaterTakeoutPoiOpenTimeUpdateRequest request = new CipCaterTakeoutPoiOpenTimeUpdateRequest();
        request.setRequestSysParams(MyUtil.getParams(token));
        request.setOpenTime(openTime);
        try {
            return request.doRequest();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object queryDelayDispatch(String token) {
        CipCaterTakeoutPoiDelayDispatchQueryRequest request = new CipCaterTakeoutPoiDelayDispatchQueryRequest();
        request.setRequestSysParams(MyUtil.getParams(token));
        try {
            return request.doRequest();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object updateDelayDispatch(String token, Integer delaySeconds) {
        CipCaterTakeoutPoiDelayDispatchUpdateRequest request = new CipCaterTakeoutPoiDelayDispatchUpdateRequest();
        request.setRequestSysParams(MyUtil.getParams(token));
        request.setDelaySeconds(delaySeconds);
        try {
            return request.doRequest();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object queryPoiInfo(String token, String ePoiIds) {
        CipCaterTakeoutPoiInfoQueryRequest request = new CipCaterTakeoutPoiInfoQueryRequest();
        request.setRequestSysParams(MyUtil.getParams(token));
        request.setEPoiIds(ePoiIds);
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
     * 查询单个门店信息,返回门店信息对象
     * @param token
     * @param ePoiId
     * @return
     */
    @Override
    public MTShop getShopInfoByePoiId(String token, String ePoiId) {
        String re = (String) queryPoiInfo(token,ePoiId);
        List shopsInfo = JsonUtil.getShopInfoJson(re);
        MTShop shop = new MTShop();
        for (int i=0;i<shopsInfo.size();i++){
            Map map = (Map) shopsInfo.get(i);
            shop.setEPoiId((String) map.get("ePoiId"));
            shop.setName((String) map.get("name"));
            shop.setNoticeInfo((String) map.get("noticeInfo"));
            shop.setAddress((String) map.get("address"));
            shop.setTagName((String) map.get("tagName"));
            shop.setLatitude(map.get("latitude").toString());
            shop.setLongitude(map.get("longitude").toString());
            shop.setPhone(map.get("phone").toString());
            shop.setPictureUrl(map.get("pictureUrl").toString());
            shop.setIsOpen((Integer) map.get("isOpen"));
            shop.setIsOnline((Integer) map.get("isOnline"));
            shop.setOpenTime(map.get("openTime").toString());
            shop.setShippingFee(Float.parseFloat(map.get("shippingFee").toString()));
            shop.setInvoiceSupport((Integer) map.get("invoiceSupport"));
            shop.setInvoiceMinPrice((Integer) map.get("invoiceMinPrice"));
            shop.setInvoiceDescription((String) map.get("invoiceDescription"));
            shop.setPreBook((Integer) map.get("preBook"));
            shop.setPreBookMinDays((Integer) map.get("preBookMinDays"));
            shop.setPreBookMaxDays((Integer) map.get("preBookMaxDays"));
            shop.setTimeSelect((Integer) map.get("timeSelect"));
        }
        return shop;
    }

    @Override
    public com.cinsc.meituan.entity.Shop getDBShopInfo(String ePoiId) {
        com.cinsc.meituan.entity.Shop shop = shopRepository.findByShopId(Long.parseLong(ePoiId));
        return shop;
    }

    @Override
    public Object getReleaseBindingUrl(String ePoiId) {
        User user = userService.getCurrentUser();
        com.cinsc.meituan.entity.Shop shop = shopRepository.findByShopId(Long.parseLong(ePoiId));
        if(user.getUserId()==shop.getUserId()){
            String releaseUrl = "https://open-erp.meituan.com/releasebinding?signKey="+MyUtil.signKey+
                    "&businessId=2&appAuthToken="+shop.getAppAuthToken();
            return ResultVOUtil.success(releaseUrl);
        }
        return ResultVOUtil.error(ResultEnum.NOT_BELONG_TO_CURRENT_USER);
    }

    @Override
    public Object autoConfirmOrder() {
        User user = userService.getCurrentUser();
        List<com.cinsc.meituan.entity.Shop> shopList = shopRepository.findByUserId(user.getUserId());
        for (com.cinsc.meituan.entity.Shop shop : shopList){
            shop.setIsAutoConfirmOrder(1);
            shopRepository.save(shop);
        }
        return ResultVOUtil.success();
    }

    @Override
    public Object cancelAutoConfirmOrder() {
        User user = userService.getCurrentUser();
        List<com.cinsc.meituan.entity.Shop> shopList = shopRepository.findByUserId(user.getUserId());
        for (com.cinsc.meituan.entity.Shop shop : shopList){
            shop.setIsAutoConfirmOrder(0);
            shopRepository.save(shop);
        }
        return ResultVOUtil.success();
    }


}
