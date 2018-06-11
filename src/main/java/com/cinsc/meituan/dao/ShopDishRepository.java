package com.cinsc.meituan.dao;

import com.cinsc.meituan.entity.ShopDish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopDishRepository extends JpaRepository<ShopDish,Long> {
    List<ShopDish> findByShopId(long shopId);
}
