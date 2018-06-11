package com.cinsc.meituan.service.serviceImpl;

import com.cinsc.meituan.DTO.Category;
import com.cinsc.meituan.service.CategoryService;
import com.cinsc.meituan.util.JsonUtil;
import com.cinsc.meituan.util.MyUtil;
import com.sankuai.sjst.platform.developer.domain.RequestSysParams;
import com.sankuai.sjst.platform.developer.request.CipCaterTakeoutDishCatDeleteRequest;
import com.sankuai.sjst.platform.developer.request.CipCaterTakeoutDishCatListQueryRequest;
import com.sankuai.sjst.platform.developer.request.CipCaterTakeoutDishCatUpdateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    /**
     * 添加菜品分类
     * @param token
     * @param sequence
     * @param catName
     * @return
     */
    @Override
    public Object addCat(String token, Integer sequence, String catName) {
        RequestSysParams requestSysParams = new RequestSysParams(MyUtil.signKey, token);
        CipCaterTakeoutDishCatUpdateRequest request  = new CipCaterTakeoutDishCatUpdateRequest();
        request.setRequestSysParams(requestSysParams);
        request.setSequence(sequence);
        request.setCatName(catName);
        try {

            return request.doRequest();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 更新菜品分类
     * @param token
     * @param oldCatName
     * @param catName
     * @return
     */
    @Override
    public Object updateCat(String token, String oldCatName, String catName) {
        RequestSysParams sysParams = new RequestSysParams(MyUtil.signKey, token);
        CipCaterTakeoutDishCatUpdateRequest request = new CipCaterTakeoutDishCatUpdateRequest();
        request.setRequestSysParams(sysParams);
        request.setOldCatName(oldCatName);
        request.setCatName(catName);
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
    public List<Category> queryCatList(String token) {
        RequestSysParams params = new RequestSysParams(MyUtil.signKey,token);
        CipCaterTakeoutDishCatListQueryRequest request = new CipCaterTakeoutDishCatListQueryRequest();
        request.setRequestSysParams(params);
        try {
            String re = request.doRequest();
            log.info("解析菜品分类json");
            List categories = JsonUtil.getCatJson(re);
            List<Category> categoryList = new ArrayList<>();
            for (int i=0;i<categories.size();i++){
                Map map = (Map) categories.get(i);
                log.info(map.toString());
                Category category = new Category();
                category.setEPoiId((String) map.get("ePoiId"));
                category.setName((String) map.get("name"));
                category.setSequence((Integer) map.get("sequence"));
                categoryList.add(category);
            }
            return categoryList;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object deleteCat(String token, String catName) {
        RequestSysParams params = new RequestSysParams(MyUtil.signKey,token);
        CipCaterTakeoutDishCatDeleteRequest request = new CipCaterTakeoutDishCatDeleteRequest();
        request.setRequestSysParams(params);
        request.setCatName(catName);
        try {
            return request.doRequest();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }


}
