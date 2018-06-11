package com.cinsc.meituan.dao;

import com.cinsc.meituan.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop,Long> {
    Shop findByShopId(long shopId);
    List<Shop> findByUserId(long userId);
}
