package com.cinsc.meituan.dao;

import com.cinsc.meituan.entity.Sku;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkuRepository extends JpaRepository<Sku,Long> {
    List<Sku> findByDishId(long dishId);
    Sku findBySkuId(long skuId);
}
