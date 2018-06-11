package com.cinsc.meituan.dao;

import com.cinsc.meituan.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property,Long>{
    Property findByPropertyId(long propertyId);
    Property deleteByPropertyId(long propertyId);
    List<Property> findByDishId(long dishId);
}
