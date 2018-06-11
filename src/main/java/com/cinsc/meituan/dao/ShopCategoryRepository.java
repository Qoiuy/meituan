package com.cinsc.meituan.dao;

import com.cinsc.meituan.entity.ShopCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopCategoryRepository extends JpaRepository<ShopCategory,Long> {
    List<ShopCategory> findByShopId(long shopId);//查询门店的所有菜品分类
}
