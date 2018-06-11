package com.cinsc.meituan.dao;

import com.cinsc.meituan.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish,Long>{
    Dish deleteByDishId(long dishId);
    Dish findByDishId(long dishId);
    Dish findByCategoryId(long categoryId);

}
