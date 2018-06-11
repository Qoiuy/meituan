package com.cinsc.meituan.dao;

import com.cinsc.meituan.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CategoryRepository extends JpaRepository<Category,Long>{
    Category findByCategoryId(long categoryId);
    Category findByCategoryName(String catName);
    Category deleteByCategoryId(long categoryId);
}
